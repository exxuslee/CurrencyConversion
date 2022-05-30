package com.mayokunadeniyi.domain.usecases


import com.exxuslee.domain.models.Price

/**
 * Created by Mayokun Adeniyi on 22/07/2020.
 */

interface GetCardInfoUseCase {
    suspend operator fun invoke(param: Int, getFromRemote: Boolean): Result<Price>
}