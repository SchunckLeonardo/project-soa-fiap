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
import java.time.LocalDateTime

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
        val instructor = findInstructorById(instructorId)

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
        val instructor = findInstructorById(instructorId)

        instructor.status = StatusEnum.INACTIVE

        instructorRepository.save(
            instructor
        )
    }

    fun findInstructorById(id: Long): Instructor =
        instructorRepository.findById(id).orElseThrow {
            throw EntityNotFoundException("Instructor with id $id not found")
        }

    fun findAvailableInstructor(dateTime: LocalDateTime): Instructor {
        val availableInstructors = instructorRepository.findAvailableInstructors(dateTime)

        if (availableInstructors.isEmpty()) {
            throw Exception("No available instructors")
        }

        return availableInstructors.random()
    }

}