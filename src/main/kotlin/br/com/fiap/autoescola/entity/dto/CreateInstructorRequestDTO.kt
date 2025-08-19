package br.com.fiap.autoescola.entity.dto

import br.com.fiap.autoescola.entity.Address
import br.com.fiap.autoescola.entity.enum.SpecialityEnum

data class CreateInstructorRequestDTO(
    val name: String,
    val email: String,
    val cnh: String,
    val speciality: SpecialityEnum,
    val address: Address
)