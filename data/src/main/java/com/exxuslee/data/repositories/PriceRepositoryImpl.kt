package com.exxuslee.data.repositories

import com.exxuslee.data.local.dao.CurrencyDao
import com.exxuslee.data.local.dao.PriceDao
import com.exxuslee.data.local.entities.PriceEntity
import com.exxuslee.data.mapper.CurrencyMapper
import com.exxuslee.data.mapper.PriceMapperLocal
import com.exxuslee.data.mapper.PriceMapperRemote
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
        return when {
            getFromRemote -> {
                val priceResult = PriceApi.getPrice(base = base, symbols = symbols)
                if (priceResult.isSuccessful) {
                    val mapperRemote = PriceMapperRemote()
                    val remoteData = priceResult.body()
                    if (remoteData != null) {
                        PriceDao.savePrice(
                            PriceEntity(
                                base = remoteData.base,
                                date = remoteData.date,
                                rates = remoteData.rates
                            )
                        )
                        Result.Success(mapperRemote.transform(remoteData))
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
                    val mapperLocal = PriceMapperLocal()
                    Result.Success(mapperLocal.transform(localData))
                }
            }
        }
    }

    override suspend fun getCurrencies(getFromRemote: Boolean): Result<List<Symbols>> {
        return when {
            getFromRemote -> {
                val priceResult = PriceApi.getCurrency()
                if (priceResult.isSuccessful) {
                    //val mapperRemote = MapperRemote()
                    val remoteData = priceResult.body()
                    if (remoteData != null) {
                        CurrencyDao.saveCurrency(
                            mapperRemote.transformToRepository(remoteData)
                        )
                        Result.Success(mapperRemote.transform(remoteData))
                    } else {
                        Result.Success(null)
                    }
                } else {
                    Result.Error(Exception("Invalid data/failure"))
                }
            }
            else -> {
                val localData = CurrencyDao.getCurrency()
                if (localData == null) {
                    Result.Success(null)
                } else {
                    val currencyMapper = CurrencyMapper()
                    Result.Success(currencyMapper.transform(localData))
                }
            }
        }
    }

    override suspend fun saveCurrencies(symbols: Symbols) {
        val currencyMapper = CurrencyMapper()
        CurrencyDao.saveCurrency(currencyMapper.transformToRepository(symbols))
    }
}