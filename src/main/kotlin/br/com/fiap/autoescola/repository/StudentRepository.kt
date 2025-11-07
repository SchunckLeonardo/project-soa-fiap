package br.com.fiap.autoescola.repository

import br.com.fiap.autoescola.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface StudentRepository: JpaRepository<Student, Long> {
    @Query("""
        SELECT s FROM Student s WHERE s.id = :studentId AND s.status = 'ACTIVE'
    """)
    fun findActiveStudentById(studentId: Long): Optional<Student>
}