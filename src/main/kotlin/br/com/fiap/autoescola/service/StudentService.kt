package br.com.fiap.autoescola.service

import br.com.fiap.autoescola.entity.dto.CreateStudentRequestDTO
import br.com.fiap.autoescola.entity.dto.CreateStudentResponseDTO
import br.com.fiap.autoescola.entity.dto.ListStudentsResponseDTO
import br.com.fiap.autoescola.entity.dto.toStudent
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

}
