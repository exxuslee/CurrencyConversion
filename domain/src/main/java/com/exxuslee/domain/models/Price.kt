package com.exxuslee.domain.models

/**
 *  "base":"EUR","date": "2022-05-30","rates": {"AED": 3.953646,"AFN": 95.522607,
 */

data class Price(
    val base: String,
    val date: String,
//    val rates: Map<String, Double>
)