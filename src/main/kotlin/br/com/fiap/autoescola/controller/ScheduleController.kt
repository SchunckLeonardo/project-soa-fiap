package br.com.fiap.autoescola.controller

import br.com.fiap.autoescola.entity.dto.ScheduleDTO
import br.com.fiap.autoescola.entity.dto.ScheduleInstructionRequestDTO
import br.com.fiap.autoescola.service.ScheduleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/schedule")
class ScheduleController(
    private val scheduleService: ScheduleService
) {

    @PostMapping
    fun instructionSchedule(
        @RequestBody request: ScheduleInstructionRequestDTO
    ): ResponseEntity<ScheduleDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            scheduleService.instructionSchedule(request)
        )
    }

    @DeleteMapping("/{id}")
    fun cancelInstructionSchedule(
        @PathVariable id: Long,
        @RequestHeader reason: String
    ): ResponseEntity<Void> {
        scheduleService.cancelInstructionSchedule(id, reason)
        return ResponseEntity.noContent().build()
    }

}