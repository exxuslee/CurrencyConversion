package com.exxuslee.data.remote.response

import androidx.collection.ArrayMap
import com.exxuslee.data.local.entities.CurrencyEntity

data class CurrencyResponse(
    val symbols: ArrayMap<String, String>,
)


/**
 * This helps to convert [CurrencyResponse] to [CurrencyEntity]
 */
fun CurrencyResponse.toEntity() =
    CurrencyEntity(
        symbols = symbols,
        favorite = arrayListOf(),
        base = ""
    )