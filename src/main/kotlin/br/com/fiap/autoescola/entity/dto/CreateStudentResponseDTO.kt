package br.com.fiap.autoescola.entity.dto

data class CreateStudentResponseDTO(
    val id: Long? = null,
    val name: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val cpf: String? = null
)
