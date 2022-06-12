package com.exxuslee.domain.models

import androidx.collection.ArrayMap

/**
 * {"success": true, "symbols": {"AED": "United Arab Emirates Dirham",
 */

data class Symbols(
    val currency: ArrayMap<String, String>,
//    val favorite: ArrayList<Boolean>,
)