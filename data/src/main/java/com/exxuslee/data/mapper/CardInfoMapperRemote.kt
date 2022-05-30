package com.exxuslee.data.mapper

import com.exxuslee.data.remote.response.PriceResponse
import com.exxuslee.domain.models.Price


/**
 * Created by Exxus Lee on 24/07/2020.
 */

class PriceMapperRemote : BaseMapperRepository<PriceResponse, Price> {
    override fun transform(type: PriceResponse): Price = Price(
        type.base ?: "", type.date ?: "", type.rate
    )

    override fun transformToRepository(type: Price): PriceResponse = PriceResponse(
        base = type.base, date = type.date, rate = type.rates
    )
}