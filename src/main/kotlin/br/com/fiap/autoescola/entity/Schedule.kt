package br.com.fiap.autoescola.entity

import br.com.fiap.autoescola.entity.dto.ScheduleDTO
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "tb_schedule")
data class Schedule(

    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    val instructor: Instructor? = null,

    @ManyToOne
    @JoinColumn(name = "student_id")
    val student: Student? = null,

    val dateTime: LocalDateTime? = null,

    @Column(name = "dh_created")
    val dhCreated: LocalDateTime = LocalDateTime.now()
)

fun Schedule.toScheduleDTO(): ScheduleDTO =
    ScheduleDTO(
        student = student?.toListStudentResponseDTO(),
        instructor = instructor?.toListInstructorResponseDTO(),
        dateTime = dateTime
    )