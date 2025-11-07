package br.com.fiap.autoescola.service

import br.com.fiap.autoescola.entity.Student
import br.com.fiap.autoescola.entity.dto.CreateStudentRequestDTO
import br.com.fiap.autoescola.entity.dto.CreateStudentResponseDTO
import br.com.fiap.autoescola.entity.dto.ListStudentsResponseDTO
import br.com.fiap.autoescola.entity.dto.UpdateStudentRequestDTO
import br.com.fiap.autoescola.entity.dto.toAddress
import br.com.fiap.autoescola.entity.dto.toStudent
import br.com.fiap.autoescola.entity.enum.StatusEnum
import br.com.fiap.autoescola.entity.toCreateStudentResponseDTO
import br.com.fiap.autoescola.entity.toListStudentResponseDTO
import br.com.fiap.autoescola.repository.StudentRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val studentRepository: StudentRepository
) {

    fun createStudent(student: CreateStudentRequestDTO): CreateStudentResponseDTO =
        studentRepository.save(
            student.toStudent()
        ).toCreateStudentResponseDTO()

    fun listStudentsPaginated(
        page: Int,
        size: Int
    ): List<ListStudentsResponseDTO> {
        val pageable = PageRequest.of(
            page,
            size,
            Sort.Direction.ASC,
            "name"
        )
        return studentRepository.findAll(pageable).map { it.toListStudentResponseDTO() }.toList()
    }

    fun updateStudent(studentId: Long, request: UpdateStudentRequestDTO) {
        val student = findActiveStudentById(studentId)

        studentRepository.save(
            student.copy(
                name = request.name ?: student.name,
                phone = request.phone ?: student.phone,
                address = request.address?.let { addr ->
                    student.address?.copy(
                        street = addr.street ?: student.address.street,
                        number = addr.number ?: student.address.number,
                        complement = addr.complement ?: student.address.complement,
                        neighborhood = addr.neighborhood ?: student.address.neighborhood,
                        city = addr.city ?: student.address.city,
                        state = addr.state ?: student.address.state,
                        cep = addr.cep ?: student.address.cep
                    ) ?: addr.toAddress()
                } ?: student.address
            )
        )
    }

    fun inactiveStudent(studentId: Long) {
        val student = findActiveStudentById(studentId)
        studentRepository.save(
            student.copy(
                status = StatusEnum.INACTIVE
            )
        )
    }

    fun findActiveStudentById(id: Long): Student =
        studentRepository.findActiveStudentById(id).orElseThrow {
            throw Exception("Student with id $id not found")
        }

}
