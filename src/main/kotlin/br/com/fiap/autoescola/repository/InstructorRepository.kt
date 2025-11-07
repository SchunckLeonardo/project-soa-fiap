package br.com.fiap.autoescola.repository

import br.com.fiap.autoescola.entity.Instructor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface InstructorRepository : JpaRepository<Instructor, Long> {

    @Query("""
        SELECT i FROM Instructor i
        WHERE i.status = 'ACTIVE'
        AND i.id NOT IN (
            SELECT s.instructor.id FROM Schedule s
            WHERE s.dateTime = :dateTime
        )
    """)
    fun findAvailableInstructors(dateTime: LocalDateTime): List<Instructor>

}