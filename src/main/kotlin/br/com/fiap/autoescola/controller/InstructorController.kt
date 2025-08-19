package br.com.fiap.autoescola.controller

import br.com.fiap.autoescola.entity.dto.CreateInstructorRequestDTO
import br.com.fiap.autoescola.entity.dto.InstructorResponseDTO
import br.com.fiap.autoescola.service.InstructorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/instructor")
class InstructorController(
    private val instructorService: InstructorService
) {

    @PostMapping
    fun registerInstructor(
        @RequestBody instructor: CreateInstructorRequestDTO
    ): ResponseEntity<InstructorResponseDTO> {
        val instructorCreated = instructorService.createInstructor(instructor)
        val uri = URI.create("/instructor/${instructorCreated.id}")

        return ResponseEntity.created(uri).body(instructorCreated)
    }

}