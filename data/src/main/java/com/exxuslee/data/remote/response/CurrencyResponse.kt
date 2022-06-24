package com.exxuslee.data.remote.response

import androidx.collection.ArrayMap

/**
 * {"success": true, "symbols": {"AED": "United Arab Emirates Dirham",
 */

data class CurrencyResponse(
    val symbols: ArrayMap<String, String>,
)