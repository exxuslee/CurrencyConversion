package com.exxuslee.data.mapper

import androidx.collection.ArrayMap
import com.exxuslee.data.local.entities.CurrencyEntity
import com.exxuslee.data.local.entities.PriceEntity
import com.exxuslee.domain.models.Price
import com.exxuslee.domain.models.Symbols


class CurrencyMapperLocal : BaseMapperRepository<CurrencyEntity, Symbols> {
    override fun transform(type: CurrencyEntity): Symbols = Symbols(
        type.symbols
    )

    override fun transformToRepository(type: Symbols): CurrencyEntity = CurrencyEntity(
        symbols = type.currency
    )
}