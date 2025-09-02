package br.com.fiap.autoescola.entity.dto

import br.com.fiap.autoescola.entity.Student
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateStudentRequestDTO(
    @field:NotBlank
    val name: String? = null,

    @field:NotBlank
    @field:Email
    val email: String? = null,

    @field:NotBlank
    val phone: String? = null,

    @field:NotBlank
    val cpf: String? = null,

    @field:NotNull
    @field:Valid
    val address: AddressDTO? = null
)

fun CreateStudentRequestDTO.toStudent(): Student {
    return Student(
        name = name,
        email = email,
        phone = phone,
        cpf = cpf,
        address = address?.toAddress()
    )
}