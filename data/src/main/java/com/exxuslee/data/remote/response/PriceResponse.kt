package com.exxuslee.data.remote.response

import com.exxuslee.data.local.entities.PriceEntity


data class PriceResponse(
    val base: String = "",
    val date: String = "",
    val rate: Map<String, Double> = mapOf(Pair("", 0.0)),
)

/**
 * This helps to convert [PriceResponse] to [PriceEntity]
 */
fun PriceResponse.toEntity() =
    PriceEntity(base = base, date = date, rate = rate)