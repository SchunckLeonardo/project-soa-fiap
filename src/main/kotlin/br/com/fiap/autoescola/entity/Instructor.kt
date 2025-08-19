package br.com.fiap.autoescola.entity

import br.com.fiap.autoescola.Address
import br.com.fiap.autoescola.entity.dto.InstructorResponseDTO
import br.com.fiap.autoescola.entity.enum.SpecialityEnum
import br.com.fiap.autoescola.toAddressDTO
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "tb_instructor")
@Entity
data class Instructor(

    @Id
    @Column(name = "instructor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val name: String? = null,

    @Column
    val cnh: String? = null,

    @Column
    @Enumerated(EnumType.STRING)
    val speciality: SpecialityEnum? = SpecialityEnum.EMPTY,

    @Column
    @Embedded
    val address: Address? = null

)

fun Instructor.toInstructorResponseDTO(): InstructorResponseDTO {
    return InstructorResponseDTO(
        id = id,
        name = name,
        cnh = cnh,
        speciality = speciality,
        address = address?.toAddressDTO()
    )
}