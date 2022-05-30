package com.exxuslee.domain.repositories

import com.exxuslee.domain.models.Price
import com.exxuslee.domain.utils.Result

/**
 * Created by Mayokun Adeniyi on 22/07/2020.
 */

interface ConversionRepository {
    suspend fun getPrice(getFromRemote: Boolean): Result<Price>
}