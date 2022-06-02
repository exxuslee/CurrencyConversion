package com.exxuslee.domain.usecases

import com.exxuslee.domain.models.Price
import com.exxuslee.domain.repositories.PriceRepository
import com.exxuslee.domain.utils.Result

interface GetPriceUseCase {
    suspend operator fun invoke(
        base: String,
        symbols: String,
        getFromRemote: Boolean,
    ): Result<Price>

    class Base(private val repository: PriceRepository) : GetPriceUseCase {
        override suspend fun invoke(base: String, symbols: String, getFromRemote: Boolean) =
            repository.getPrice(base, symbols, getFromRemote)
    }
}