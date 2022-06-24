package com.exxuslee.data.remote.response

import androidx.collection.ArrayMap

data class PriceResponse(
    val base: String = "",
    val date: String = "",
    val rates: ArrayMap<String, Double>
)