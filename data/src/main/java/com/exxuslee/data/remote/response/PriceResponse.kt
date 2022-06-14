package com.exxuslee.data.remote.response

import androidx.collection.ArrayMap
import com.exxuslee.data.local.entities.PriceEntity

data class PriceResponse(
    val base: String = "",
    val date: String = "",
    val rates: ArrayMap<String, Double>
)

/**
 * This helps to convert [PriceResponse] to [PriceEntity]
 */
fun PriceResponse.toEntity() =
    PriceEntity(base = base, date = date, rates = rates)