package com.exxuslee.domain.models

import androidx.collection.ArrayMap
import java.io.Serializable

/**
 * {"success": true, "symbols": {"AED": "United Arab Emirates Dirham",
 */

data class Symbols(
    val symbols: ArrayMap<String, String>,
    val favorite: ArrayMap<String, Boolean>,
    val base: String,
): Serializable