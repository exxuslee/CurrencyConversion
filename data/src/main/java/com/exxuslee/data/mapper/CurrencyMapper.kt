package com.exxuslee.data.mapper

import com.exxuslee.data.local.entities.CurrencyEntity
import com.exxuslee.data.remote.response.CurrencyResponse
import com.exxuslee.domain.models.Symbols


class CurrencyMapper : BaseMapper<List<Symbols>, List<CurrencyEntity>, CurrencyResponse> {

    override fun domainToLocal(type: List<Symbols>): List<CurrencyEntity> {
        val asd: ArrayList<CurrencyEntity> = arrayListOf()
        type.map { Symbols ->
            asd.add(CurrencyEntity(
                xxx = Symbols.xxx,
                name = Symbols.name,
                base = Symbols.base,
                check = Symbols.check
            ))
        }
        return asd
    }

    override fun localToDomain(type: List<CurrencyEntity>): List<Symbols> {
        val asd: ArrayList<Symbols> = arrayListOf()
        type.map { CurrencyEntity ->
            asd.add(Symbols(
                xxx = CurrencyEntity.xxx,
                name = CurrencyEntity.name,
                base = CurrencyEntity.base,
                check = CurrencyEntity.check
            ))
        }
        return asd
    }

    override fun remoteToLocal(type: CurrencyResponse): List<CurrencyEntity> {
        val asd: ArrayList<CurrencyEntity> = ArrayList()
        type.symbols.map { (xxx, name) -> asd.add(CurrencyEntity(
                xxx = xxx,
                name = name,
                base = xxx == "EUR",
                check = xxx == "BTC" || xxx == "UAH" || xxx == "USD"
            ))
        }
        return asd
    }

    override fun remoteToDomain(type: CurrencyResponse): List<Symbols> {
        val asd: ArrayList<Symbols> = arrayListOf()
        type.symbols.map { (xxx, name) ->
            asd.add(Symbols(
                xxx = xxx,
                name = name,
                base = xxx == "EUR",
                check = xxx == "BTC" || xxx == "UAH" || xxx == "USD"
            ))
        }
        return asd
    }
}