package br.com.fiap.autoescola.entity.dto

import br.com.fiap.autoescola.Address

data class AddressDTO(
    val street: String? = null,
    val number: String? = null,
    val complement: String? = null,
    val neighborhood: String? = null,
    val city: String? = null,
    val state: String? = null,
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