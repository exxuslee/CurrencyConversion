package com.exxuslee.domain.models

import java.io.Serializable

/**
 * {"success": true, "symbols": {"AED": "United Arab Emirates Dirham",
 */

data class Symbols(
    val symbol: List<Symbol>,
): Serializable {
    data class Symbol(
        val xxx: String,
        val name: String,
        val base: Boolean,
        val check: Boolean,
    )
}