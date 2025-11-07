package br.com.fiap.autoescola.entity

import br.com.fiap.autoescola.entity.dto.CreateStudentResponseDTO
import br.com.fiap.autoescola.entity.dto.ListStudentsResponseDTO
import br.com.fiap.autoescola.entity.enum.StatusEnum
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tb_student")
data class Student(

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val name: String? = null,

    @Column
    val email: String? = null,

    @Column
    val phone: String? = null,

    @Column
    @Enumerated(EnumType.STRING)
    var status: StatusEnum? = StatusEnum.ACTIVE,

    @Column
    val cpf: String? = null,

    @Column
    @Embedded
    val address: Address? = null

)

fun Student.toCreateStudentResponseDTO(): CreateStudentResponseDTO {
    return CreateStudentResponseDTO(
        id = id,
        name = name,
        email = email,
        phone = phone,
        cpf = cpf
    )
}

fun Student.toListStudentResponseDTO(): ListStudentsResponseDTO {
    return ListStudentsResponseDTO(
        name = name,
        email = email,
        cpf = cpf
    )
}