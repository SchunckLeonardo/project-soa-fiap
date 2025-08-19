package br.com.fiap.autoescola.controller

import br.com.fiap.autoescola.entity.dto.CreateInstructorRequestDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/instructor")
class InstructorController {

    @PostMapping
    fun registerInstructor(
        @RequestBody instructor: CreateInstructorRequestDTO
    ): ResponseEntity<Any> {
        return ResponseEntity.ok().build()
    }

}