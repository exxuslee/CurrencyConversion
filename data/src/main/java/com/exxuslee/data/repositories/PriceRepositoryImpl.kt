package com.exxuslee.data.repositories

import com.exxuslee.data.local.dao.CurrencyDao
import com.exxuslee.data.local.dao.PriceDao
import com.exxuslee.data.local.entities.CurrencyEntity
import com.exxuslee.data.local.entities.PriceEntity
import com.exxuslee.data.mapper.CurrencyMapper
import com.exxuslee.data.mapper.PriceMapper
import com.exxuslee.data.remote.api.PriceApiService
import com.exxuslee.domain.models.Price
import com.exxuslee.domain.models.Symbols
import com.exxuslee.domain.repositories.PriceRepository
import com.exxuslee.domain.utils.Result

class PriceRepositoryImpl(
    private val PriceApi: PriceApiService,
    private val PriceDao: PriceDao,
    private val CurrencyDao: CurrencyDao,
) : PriceRepository {

    override suspend fun getPrice(
        base: String,
        symbols: String,
        getFromRemote: Boolean,
    ): Result<Price> {
        val mapper = PriceMapper()
        return when {
            getFromRemote -> {
                val priceResult = PriceApi.getPrice(base = base, symbols = symbols)
                if (priceResult.isSuccessful) {
                    val remoteData = priceResult.body()
                    if (remoteData != null) {
                        PriceDao.savePrice(
                            PriceEntity(
                                base = remoteData.base,
                                date = remoteData.date,
                                rates = remoteData.rates
                            )
                        )
                        Result.Success(mapper.remoteToDomain(remoteData))
                    } else {
                        Result.Success(null)
                    }
                } else {
                    Result.Error(Exception("Invalid data/failure"))
                }
            }
            else -> {
                val localData = PriceDao.getPrice(base)
                if (localData == null) {
                    Result.Success(null)
                } else {
                    Result.Success(mapper.localToDomain(localData))
                }
            }
        }
    }

    override suspend fun getCurrencies(getFromRemote: Boolean): Result<Symbols> {
        val mapper = CurrencyMapper()
        return when {
            getFromRemote -> {
                val priceResult = PriceApi.getCurrency()
                if (priceResult.isSuccessful) {
                    val remoteData = priceResult.body()
                    if (remoteData != null) {
                        mapper.remoteToLocal(remoteData).map { CurrencyDao.saveCurrency(it) }
                        Result.Success(mapper.remoteToDomain(remoteData))
                    } else {
                        Result.Success(null)
                    }
                } else {
                    Result.Error(Exception("Invalid data/failure"))
                }
            }
            else -> {
                val localData = CurrencyDao.getCurrency()
                if (localData == null || localData.isEmpty()) {
                    Result.Success(null)
                } else {
                    Result.Success(mapper.localToDomain(localData))
                }
            }
        }
    }

    override suspend fun saveCurrencies(symbol: Symbols.Symbol) {
        CurrencyDao.saveCurrency(CurrencyEntity(
            xxx = symbol.xxx,
            name = symbol.name,
            base = symbol.base,
            check = symbol.check
        ))
    }

    override suspend fun saveAllCurrencies(symbols: Symbols) {
        symbols.symbol.map { saveCurrencies(it) }
    }
}