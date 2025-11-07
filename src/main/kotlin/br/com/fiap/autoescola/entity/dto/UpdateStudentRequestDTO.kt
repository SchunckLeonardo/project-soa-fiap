package br.com.fiap.autoescola.entity.dto

data class UpdateStudentRequestDTO(
    val name: String? = null,
    val phone: String? = null,
    val address: AddressDTO? = null
)
