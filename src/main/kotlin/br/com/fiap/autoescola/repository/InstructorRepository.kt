package br.com.fiap.autoescola.repository

import br.com.fiap.autoescola.entity.Instructor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface InstructorRepository : CrudRepository<Instructor, Long> {}