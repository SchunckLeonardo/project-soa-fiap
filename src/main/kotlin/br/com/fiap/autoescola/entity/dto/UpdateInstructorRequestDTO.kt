package br.com.fiap.autoescola.entity.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class UpdateInstructorRequestDTO(
    @field:NotBlank
    val name: String? = "",

    @field:NotBlank
    val phone: String? = "",

    @field:NotNull
    @field:Valid
    val address: AddressDTO? = null
)
