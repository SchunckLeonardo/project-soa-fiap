package br.com.fiap.autoescola.controller

import br.com.fiap.autoescola.entity.dto.CreateInstructorRequestDTO
import br.com.fiap.autoescola.entity.dto.InstructorResponseDTO
import br.com.fiap.autoescola.entity.dto.ListInstructorResponseDTO
import br.com.fiap.autoescola.entity.dto.UpdateInstructorRequestDTO
import br.com.fiap.autoescola.service.InstructorService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/instructors")
class InstructorController(
    private val instructorService: InstructorService
) {

    @PostMapping
    fun registerInstructor(
        @RequestBody @Valid instructor: CreateInstructorRequestDTO
    ): ResponseEntity<InstructorResponseDTO> {
        val instructorCreated = instructorService.createInstructor(instructor)
        val uri = URI.create("/instructors/${instructorCreated.id}")

        return ResponseEntity.created(uri).body(instructorCreated)
    }

    @GetMapping
    fun getInstructors(
        @RequestParam(
            defaultValue = "0"
        ) page: Int,
        @RequestParam(
            defaultValue = "10"
        ) size: Int,
    ): ResponseEntity<List<ListInstructorResponseDTO>> {
        return ResponseEntity.ok(instructorService.getInstructors(page, size))
    }

    @PutMapping("/{id}")
    fun updateInstructor(
        @PathVariable id: Long,
        @RequestBody @Valid instructor: UpdateInstructorRequestDTO
    ): ResponseEntity<Any> {
        instructorService.updateInstructor(id, instructor)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun inactiveInstructor(
        @PathVariable id: Long
    ): ResponseEntity<Any> {
        instructorService.inactiveInstructor(id)
        return ResponseEntity.noContent().build()
    }

}