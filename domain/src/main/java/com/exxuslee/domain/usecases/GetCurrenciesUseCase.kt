package com.exxuslee.domain.usecases

import com.exxuslee.domain.models.Symbols
import com.exxuslee.domain.repositories.PriceRepository
import com.exxuslee.domain.utils.Result

interface GetCurrenciesUseCase {
    suspend operator fun invoke(getFromRemote: Boolean): Result<Symbols>

    class Base(private val repository: PriceRepository) : GetCurrenciesUseCase {
        override suspend fun invoke(getFromRemote: Boolean) =
            repository.getCurrencies(getFromRemote)
    }
}