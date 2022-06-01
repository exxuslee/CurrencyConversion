package com.exxuslee.data.repositories

import com.exxuslee.data.local.dao.PriceDao
import com.exxuslee.data.local.entities.PriceEntity
import com.exxuslee.data.mapper.PriceMapperLocal
import com.exxuslee.data.mapper.PriceMapperRemote
import com.exxuslee.data.remote.api.PriceApiService
import com.exxuslee.domain.models.Price
import com.exxuslee.domain.repositories.PriceRepository
import com.exxuslee.domain.utils.Result

class PriceRepositoryImpl(
    private val PriceApi: PriceApiService,
    private val PriceDao: PriceDao,
) : PriceRepository {

    override suspend fun getPrice(base:String, symbols:String, getFromRemote: Boolean): Result<Price> {
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
                                date = remoteData.date
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
}