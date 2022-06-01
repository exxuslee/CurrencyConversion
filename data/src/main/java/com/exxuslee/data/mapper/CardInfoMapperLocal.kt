package com.exxuslee.data.mapper

import com.exxuslee.data.local.entities.PriceEntity
import com.exxuslee.domain.models.Price


class PriceMapperLocal : BaseMapperRepository<PriceEntity, Price> {
    override fun transform(type: PriceEntity): Price = Price(
        type.base ?: "",
        type.date ?: ""
    )

    override fun transformToRepository(type: Price): PriceEntity = PriceEntity(
        base = type.base, date = type.date
    )
}