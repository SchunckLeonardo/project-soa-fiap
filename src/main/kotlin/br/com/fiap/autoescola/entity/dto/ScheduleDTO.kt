package br.com.fiap.autoescola.entity.dto

import java.time.LocalDateTime

data class ScheduleDTO(
    val student: ListStudentsResponseDTO?,
    val instructor: ListInstructorResponseDTO?,
    val dateTime: LocalDateTime?
)
