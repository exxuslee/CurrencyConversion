package com.exxuslee.domain.repositories

import com.exxuslee.domain.models.Price
import com.exxuslee.domain.models.Symbols
import com.exxuslee.domain.utils.Result

interface PriceRepository {
    suspend fun getPrice(base: String, symbols: String, getFromRemote: Boolean): Result<Price>
    suspend fun getCurrencies(getFromRemote: Boolean): Result<List<Symbols>>
    suspend fun saveCurrencies (symbols: Symbols)
}