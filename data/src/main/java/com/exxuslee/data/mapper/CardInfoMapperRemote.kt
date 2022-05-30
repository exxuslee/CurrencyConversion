package com.exxuslee.data.mapper

import com.exxuslee.data.remote.response.Bank
import com.exxuslee.data.remote.response.PriceResponse
import com.exxuslee.data.remote.response.Country
import com.exxuslee.domain.models.Price


/**
 * Created by Exxus Lee on 24/07/2020.
 */

class PriceMapperRemote : BaseMapperRepository<PriceResponse, Price> {
    override fun transform(type: PriceResponse): Price = Price(
        type.bank?.name ?: "", type.brand ?: "", type.country?.name ?: "", type.type ?: ""
    )

    override fun transformToRepository(type: Price): PriceResponse = PriceResponse(
        bank = Bank(type.bank), brand = type.brand, country = Country(type.country)
    )
}