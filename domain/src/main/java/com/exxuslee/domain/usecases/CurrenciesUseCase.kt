package com.exxuslee.domain.usecases

import com.exxuslee.domain.models.Symbols
import com.exxuslee.domain.repositories.PriceRepository
import com.exxuslee.domain.utils.Result

interface CurrenciesUseCase {
    suspend fun load(getFromRemote: Boolean): Result<Symbols>
    suspend fun save(symbol: Symbols.Symbol)
    suspend fun saveAll(symbols: Symbols)

    class Base(private val repository: PriceRepository) : CurrenciesUseCase {
        override suspend fun load(getFromRemote: Boolean): Result<Symbols> =
            repository.getCurrencies(getFromRemote)

        override suspend fun save(symbol: Symbols.Symbol) = repository.saveCurrencies(symbol)

        override suspend fun saveAll(symbols: Symbols) = repository.saveAllCurrencies(symbols)
    }
}