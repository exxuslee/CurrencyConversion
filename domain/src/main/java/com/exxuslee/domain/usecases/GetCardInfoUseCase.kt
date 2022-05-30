package com.exxuslee.domain.usecases

import com.exxuslee.domain.models.Price
import com.exxuslee.domain.repositories.PriceRepository
import com.exxuslee.domain.utils.Result

interface GetCardInfoUseCase {
    suspend operator fun invoke(getFromRemote: Boolean): Result<Price>

    class Base(private val repository: PriceRepository) : GetCardInfoUseCase {
        override suspend fun invoke(getFromRemote: Boolean) = repository.getPrice(getFromRemote)
    }
}