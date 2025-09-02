package br.com.fiap.autoescola.service

import br.com.fiap.autoescola.entity.Instructor
import br.com.fiap.autoescola.entity.dto.CreateInstructorRequestDTO
import br.com.fiap.autoescola.entity.dto.InstructorResponseDTO
import br.com.fiap.autoescola.entity.dto.ListInstructorResponseDTO
import br.com.fiap.autoescola.entity.dto.UpdateInstructorRequestDTO
import br.com.fiap.autoescola.entity.dto.toAddress
import br.com.fiap.autoescola.entity.enum.SpecialityEnum
import br.com.fiap.autoescola.entity.enum.StatusEnum
import br.com.fiap.autoescola.entity.toInstructorResponseDTO
import br.com.fiap.autoescola.entity.toListInstructorResponseDTO
import br.com.fiap.autoescola.repository.InstructorRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class InstructorService(
    private val instructorRepository: InstructorRepository
) {

    fun createInstructor(instructor: CreateInstructorRequestDTO): InstructorResponseDTO {
        return instructorRepository.save(
            Instructor(
                name = instructor.name,
                email = instructor.email,
                cnh = instructor.cnh,
                speciality = SpecialityEnum.getSpeciality(instructor.speciality),
                address = instructor.address?.toAddress()
            )
        ).toInstructorResponseDTO()
    }

    fun getInstructors(page: Int, size: Int): List<ListInstructorResponseDTO> {
        val pageable = PageRequest.of(
            page,
            size,
            Sort.Direction.ASC,
            "name"
        )
        return instructorRepository.findAll(pageable).map { it.toListInstructorResponseDTO() }.toList()
    }

    fun updateInstructor(instructorId: Long, instructorUpdate: UpdateInstructorRequestDTO) {
        val instructor = instructorRepository.findById(instructorId).orElseThrow {
            throw EntityNotFoundException("Instructor with id $instructorId not found")
        }

        instructorRepository.save(
            Instructor(
                id = instructorId,
                name = instructorUpdate.name ?: instructor.name,
                email = instructor.email,
                cnh = instructor.cnh,
                speciality = instructor.speciality,
                phone = instructorUpdate.phone ?: instructor.phone,
                address = instructorUpdate.address?.toAddress() ?: instructor.address
            )
        )
    }

    fun inactiveInstructor(instructorId: Long) {
        val instructor = instructorRepository.findById(instructorId).orElseThrow {
            throw EntityNotFoundException("Instructor with id $instructorId not found")
        }

        instructor.status = StatusEnum.INACTIVE

        instructorRepository.save(
            instructor
        )
    }

}