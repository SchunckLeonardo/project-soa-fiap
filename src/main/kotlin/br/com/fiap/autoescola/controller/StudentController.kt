package br.com.fiap.autoescola.controller

import br.com.fiap.autoescola.entity.dto.CreateStudentRequestDTO
import br.com.fiap.autoescola.entity.dto.CreateStudentResponseDTO
import br.com.fiap.autoescola.entity.dto.ListStudentsResponseDTO
import br.com.fiap.autoescola.service.StudentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/students")
class StudentController(
    private val studentService: StudentService
) {

    @PostMapping
    fun createStudent(@RequestBody student: CreateStudentRequestDTO): ResponseEntity<CreateStudentResponseDTO> {
        val student = studentService.createStudent(student)
        val uri = URI("/students/${student.id}")
        return ResponseEntity.created(uri).body(student)
    }

    @GetMapping
    fun listStudent(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<List<ListStudentsResponseDTO>> {
        return ResponseEntity.ok(studentService.listStudentsPaginated(page, size))
    }

}
