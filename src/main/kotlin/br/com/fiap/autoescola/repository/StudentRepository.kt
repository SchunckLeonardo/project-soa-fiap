package br.com.fiap.autoescola.repository

import br.com.fiap.autoescola.entity.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Student, Long> { }