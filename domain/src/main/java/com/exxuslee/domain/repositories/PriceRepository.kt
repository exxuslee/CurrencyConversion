package com.exxuslee.domain.repositories

import com.exxuslee.domain.models.Price
import com.exxuslee.domain.utils.Result

interface PriceRepository {
    suspend fun getPrice(getFromRemote: Boolean): Result<Price>
}