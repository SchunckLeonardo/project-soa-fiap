package br.com.fiap.autoescola.entity.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ScheduleInstructionRequestDTO(
    @field:JsonProperty("instructor_id")
    val instructorId: Long? = null,

    @field:JsonProperty("student_id")
    val studentId: Long,

    @field:JsonProperty("date_time")
    val dateTime: LocalDateTime
)
