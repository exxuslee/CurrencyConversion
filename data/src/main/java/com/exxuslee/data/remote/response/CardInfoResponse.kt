package com.exxuslee.data.remote.response

import com.exxuslee.data.local.entities.PriceEntity


data class PriceResponse(
    val bank: Bank?,
    val brand: String? = "",
    val country: Country?,
    val type: String? = ""
)

data class Bank(
    val name: String? = ""
)

data class Country(
    val name: String? = ""
)

/**
 * This helps to convert [PriceResponse] to [PriceEntity]
 */
fun PriceResponse.toEntity() =
    PriceEntity(bank = bank, country = country, type = type, brand = brand)