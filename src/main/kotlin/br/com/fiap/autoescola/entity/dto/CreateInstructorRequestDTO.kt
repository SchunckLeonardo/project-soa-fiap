package br.com.fiap.autoescola.entity.dto

data class CreateInstructorRequestDTO(
    val name: String? = null,
    val email: String? = null,
    val cnh: String? = null,
    val speciality: String? = null,
    val address: AddressDTO? = null
)