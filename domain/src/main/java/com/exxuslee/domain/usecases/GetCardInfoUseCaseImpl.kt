package com.exxuslee.domain.usecases

import com.exxuslee.domain.repositories.PriceRepository

class GetCardInfoUseCaseImpl(private val repository: PriceRepository) : GetCardInfoUseCase {

    override suspend operator fun invoke (getFromRemote: Boolean) =
        repository.getPrice(getFromRemote)
}