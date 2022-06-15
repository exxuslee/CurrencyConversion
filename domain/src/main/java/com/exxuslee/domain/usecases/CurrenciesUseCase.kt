package com.exxuslee.domain.usecases

import com.exxuslee.domain.models.Symbols
import com.exxuslee.domain.repositories.PriceRepository
import com.exxuslee.domain.utils.Result

interface CurrenciesUseCase {
    suspend fun load (getFromRemote: Boolean): Result<Symbols>
    suspend fun save (symbols: Symbols)

    class Base(private val repository: PriceRepository) : CurrenciesUseCase {
        override suspend fun load(getFromRemote: Boolean) =
            repository.getCurrencies(getFromRemote)

        override suspend fun save(symbols: Symbols) = repository.saveCurrencies(symbols)
    }
}