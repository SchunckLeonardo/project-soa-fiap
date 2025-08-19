package br.com.fiap.autoescola.entity.dto

import br.com.fiap.autoescola.entity.enum.SpecialityEnum

data class InstructorResponseDTO(
    val id: Long? = null,
    val name: String? = null,
    val cnh: String? = null,
    val speciality: SpecialityEnum? = null,
    val address: AddressDTO? = null
)
