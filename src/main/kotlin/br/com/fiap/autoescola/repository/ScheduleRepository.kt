package br.com.fiap.autoescola.repository

import br.com.fiap.autoescola.entity.Instructor
import br.com.fiap.autoescola.entity.Schedule
import br.com.fiap.autoescola.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface ScheduleRepository: JpaRepository<Schedule, Long> {

    fun findSchedulesByStudent(student: Student): List<Schedule>

    fun findSchedulesByInstructor(instructor: Instructor): List<Schedule>

}