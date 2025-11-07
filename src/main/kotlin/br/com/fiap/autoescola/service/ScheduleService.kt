package br.com.fiap.autoescola.service

import br.com.fiap.autoescola.entity.Schedule
import br.com.fiap.autoescola.entity.dto.ScheduleDTO
import br.com.fiap.autoescola.entity.dto.ScheduleInstructionRequestDTO
import br.com.fiap.autoescola.entity.toScheduleDTO
import br.com.fiap.autoescola.repository.ScheduleRepository
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

@Service
class ScheduleService(
    private val scheduleRepository: ScheduleRepository,
    private val instructorService: InstructorService,
    private val studentService: StudentService
) {

    fun instructionSchedule(request: ScheduleInstructionRequestDTO): ScheduleDTO {
        validateDateTime(request.dateTime)
        
        val instructor = request.instructorId?.let {
            instructorService.findInstructorById(it)
        } ?: instructorService.findAvailableInstructor(request.dateTime)
        val student = studentService.findActiveStudentById(request.studentId)
        
        val studentSchedules = scheduleRepository.findSchedulesByStudent(
            student = student
        )
        
        studentSchedules.forEach {
            val scheduleDate = it.dateTime?.dayOfYear
            
            if (scheduleDate == request.dateTime.dayOfYear) {
                throw Exception("Student already scheduled for this time.")
            }
        }
        
        val instructorSchedules = scheduleRepository.findSchedulesByInstructor(
            instructor = instructor
        )
        
        instructorSchedules.forEach {
            val scheduleDateTime = it.dateTime?.toLocalTime()
            val scheduleDate = it.dateTime?.dayOfYear
            
            if (scheduleDate == request.dateTime.dayOfYear &&
                scheduleDateTime == request.dateTime.toLocalTime()) {
                throw Exception("Instructor already has a class scheduled at this time.")
            }
        }
        
        return scheduleRepository.save(
            Schedule(
                instructor = instructor,
                student = student,
                dateTime = request.dateTime
            )
        ).toScheduleDTO()
    }

    fun cancelInstructionSchedule(id: Long, reason: String) {
        val normalizedReason = reason.lowercase()
        val allowedReasons = setOf("aluno desistiu", "instrutor cancelou", "outros")
        if (normalizedReason !in allowedReasons) {
            throw Exception("Invalid cancellation reason. Allowed values: aluno desistiu, instrutor cancelou, outros.")
        }

        val schedule = scheduleRepository.findById(id).orElseThrow { Exception("Schedule not found.") }
        val dateTime = schedule.dateTime ?: throw Exception("Schedule date/time is not set.")
        val now = LocalDateTime.now()

        if (dateTime.isBefore(now.plusHours(24))) {
            throw Exception("Uma instrução somente poderá ser cancelada com antecedência mínima de 24 horas.")
        }

        scheduleRepository.delete(schedule)
    }
    
    private fun validateDateTime(dateTime: LocalDateTime) {
        val dayOfWeek = dateTime.dayOfWeek
        val localTime = dateTime.toLocalTime()
        val now = LocalDateTime.now()
        val minimumTimeScheduleAllowed = now.plusMinutes(30)
        
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw Exception("It is not allowed to schedule classes in the past.")
        }
        
        if (dateTime.isBefore(minimumTimeScheduleAllowed)) {
            throw Exception("It is not allowed to schedule classes after $minimumTimeScheduleAllowed.")
        }
        
        if (dayOfWeek == DayOfWeek.SUNDAY) {
            throw Exception("Scheduling on Sundays is not allowed.")
        }
        
        if (localTime.isBefore(LocalTime.of(6, 0)) || localTime.isAfter(LocalTime.of(20, 0))) {
            throw Exception("Scheduling between 6:00 AM and 8:00 PM is allowed.")
        }
        
    }
    
}