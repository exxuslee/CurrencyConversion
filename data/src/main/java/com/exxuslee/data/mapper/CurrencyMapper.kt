package com.exxuslee.data.mapper

import com.exxuslee.data.local.entities.CurrencyEntity
import com.exxuslee.data.remote.response.CurrencyResponse
import com.exxuslee.domain.models.Symbols


class CurrencyMapper : BaseMapper<Symbols, List<CurrencyEntity>, CurrencyResponse> {

    override fun domainToLocal(type: Symbols): List<CurrencyEntity> {
        val asd: ArrayList<CurrencyEntity> = arrayListOf()
        type.symbol.map { Symbol ->
            asd.add(CurrencyEntity(
                xxx = Symbol.xxx,
                name = Symbol.name,
                base = Symbol.base,
                check = Symbol.check
            ))
        }
        return asd
    }

    override fun localToDomain(type: List<CurrencyEntity>): Symbols {
        val asd: ArrayList<Symbols.Symbol> = arrayListOf()
        type.map { CurrencyEntity ->
            asd.add(Symbols.Symbol(
                xxx = CurrencyEntity.xxx,
                name = CurrencyEntity.name,
                base = CurrencyEntity.base,
                check = CurrencyEntity.check
            ))
        }
        return Symbols(asd)
    }

    override fun remoteToLocal(type: CurrencyResponse): List<CurrencyEntity> {
        val asd: ArrayList<CurrencyEntity> = ArrayList()
        type.symbols.map { (xxx, name) ->
            asd.add(CurrencyEntity(
                xxx = xxx,
                name = name,
                base = xxx == "EUR",
                check = xxx == "BTC" || xxx == "UAH" || xxx == "USD"
            ))
        }
        return asd
    }

    override fun remoteToDomain(type: CurrencyResponse): Symbols {
        val asd: ArrayList<Symbols.Symbol> = arrayListOf()
        type.symbols.map {
            asd.add(Symbols.Symbol(
                xxx = it.key,
                name = it.value,
                base = it.key == "EUR",
                check = it.key == "BTC" || it.key == "UAH" || it.key == "USD"
            ))
        }
        return Symbols(asd)
    }
}