package com.exxuslee.data.repositories

import com.exxuslee.data.local.dao.PriceDao
import com.exxuslee.data.remote.api.PriceApiService
import com.exxuslee.domain.models.Price
import com.exxuslee.domain.repositories.PriceRepository
import com.exxuslee.domain.utils.Result

class PriceRepositoryImpl(
    private val PriceApi: PriceApiService,
    private val PriceDao: PriceDao
) : PriceRepository {

    override suspend fun getPrice(getFromRemote: Boolean): Result<Price> {
        return when {
            getFromRemote -> {
                val PriceResult = PriceApi.getPrice(cardNumber)
                if (PriceResult.isSuccessful) {
                    val mapperRemote = PriceMapperRemote()
                    val remoteData = PriceResult.body()
                    if (remoteData != null) {
                        PriceDao.savePrice(
                            PriceEntity(
                                id = cardNumber,
                                bank = remoteData.bank,
                                brand = remoteData.brand,
                                country = remoteData.country,
                                type = remoteData.type
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
                val localData = PriceDao.getPrice(cardNumber)
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