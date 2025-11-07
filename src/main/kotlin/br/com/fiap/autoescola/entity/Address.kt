package br.com.fiap.autoescola.entity

import br.com.fiap.autoescola.entity.dto.AddressDTO
import jakarta.persistence.Embeddable

@Embeddable
data class Address(

    val street: String? = null,
    val number: String? = null,
    val complement: String? = null,
    val neighborhood: String? = null,
    val city: String? = null,
    val state: String? = null,
    val cep: String? = null

)

fun Address.toAddressDTO(): AddressDTO {
    return AddressDTO(
        street = street,
        number = number,
        complement = complement,
        neighborhood = neighborhood,
        city = city,
        state = state,
        cep = cep
    )
}