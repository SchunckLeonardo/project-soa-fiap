package br.com.fiap.autoescola.entity.dto

import br.com.fiap.autoescola.Address
import jakarta.validation.constraints.NotBlank

data class AddressDTO(
    @field:NotBlank
    val street: String? = null,

    val number: String? = null,
    val complement: String? = null,

    @field:NotBlank
    val neighborhood: String? = null,

    @field:NotBlank
    val city: String? = null,

    @field:NotBlank
    val state: String? = null,

    @field:NotBlank
    val cep: String? = null
)

fun AddressDTO.toAddress(): Address {
    return Address(
        street = street,
        number = number,
        complement = complement,
        neighborhood = neighborhood,
        city = city,
        state = state,
        cep = cep
    )
}