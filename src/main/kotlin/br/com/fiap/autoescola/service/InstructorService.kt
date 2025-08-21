package br.com.fiap.autoescola.service

import br.com.fiap.autoescola.entity.Instructor
import br.com.fiap.autoescola.entity.dto.CreateInstructorRequestDTO
import br.com.fiap.autoescola.entity.dto.InstructorResponseDTO
import br.com.fiap.autoescola.entity.dto.toAddress
import br.com.fiap.autoescola.entity.enum.SpecialityEnum
import br.com.fiap.autoescola.entity.toInstructorResponseDTO
import br.com.fiap.autoescola.repository.InstructorRepository
import org.springframework.stereotype.Service

@Service
class InstructorService(
    private val instructorRepository: InstructorRepository
) {

    fun createInstructor(instructor: CreateInstructorRequestDTO): InstructorResponseDTO {
        return instructorRepository.save(
            Instructor(
                name = instructor.name,
                cnh = instructor.cnh,
                speciality = SpecialityEnum.getSpeciality(instructor.speciality),
                address = instructor.address?.toAddress()
            )
        ).toInstructorResponseDTO()
    }

}