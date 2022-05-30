package com.exxuslee.domain.models

/**
 *  "base": "EUR",
"date": "2022-05-30",
"rates": {
"AED": 3.953646,
"AFN": 95.522607,
 */

data class Price(
    val bank: String,
    val brand: String,
    val country: String,
    val type: List<Pair<String, Double>>,
)