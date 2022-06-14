package com.exxuslee.data.mapper

import com.exxuslee.data.local.entities.CurrencyEntity
import com.exxuslee.domain.models.Symbols


class CurrencyMapperLocal : BaseMapperRepository<CurrencyEntity, Symbols> {
    override fun transform(type: CurrencyEntity): Symbols = Symbols(
        symbols = type.symbols,
        favorite = type.favorite,
        base = type.base
    )

    override fun transformToRepository(type: Symbols): CurrencyEntity = CurrencyEntity(
        symbols = type.symbols,
        favorite = type.favorite,
        base = type.base
    )
}