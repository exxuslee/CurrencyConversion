package com.exxuslee.data.mapper

import com.exxuslee.data.local.entities.PriceEntity
import com.exxuslee.data.remote.response.Bank
import com.exxuslee.data.remote.response.Country
import com.exxuslee.domain.models.Price


class PriceMapperLocal : BaseMapperRepository<PriceEntity, Price> {
    override fun transform(type: PriceEntity): Price = Price(
        type.bank?.name ?: "",
        type.brand ?: "",
        type.country?.name ?: "",
        (type.type ?: Pair("", 0.0)) as Pair<String, Double>
    )

    override fun transformToRepository(type: Price): PriceEntity = PriceEntity(
        bank = Bank(type.bank), brand = type.brand, country = Country(type.country)
    )
}