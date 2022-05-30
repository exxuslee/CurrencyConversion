package com.exxuslee.domain.usecases

import com.exxuslee.domain.models.Price
import com.exxuslee.domain.repositories.PriceRepository

/**
 * Created by Mayokun Adeniyi on 23/07/2020.
 */

class GetCardInfoUseCaseImpl(private val repository: PriceRepository) : GetCardInfoUseCase {

    override suspend operator fun invoke(getFromRemote: Boolean): Result<Price>
        return repository.getPrice(getFromRemote)
}