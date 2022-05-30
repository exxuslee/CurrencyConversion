package com.exxuslee.domain.usecases

import com.exxuslee.domain.models.Price
import com.exxuslee.domain.utils.Result

interface GetCardInfoUseCase {
    suspend operator fun invoke(getFromRemote: Boolean): Result<Price>
}