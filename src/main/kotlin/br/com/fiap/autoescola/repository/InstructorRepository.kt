package br.com.fiap.autoescola.repository

import br.com.fiap.autoescola.entity.Instructor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InstructorRepository : JpaRepository<Instructor, Long> { }