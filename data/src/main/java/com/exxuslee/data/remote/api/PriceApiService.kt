package com.exxuslee.data.remote.api

import com.exxuslee.data.remote.response.CurrencyResponse
import com.exxuslee.data.remote.response.PriceResponse
import retrofit2.Response
import retrofit2.http.*

interface PriceApiService {
    @Headers("apikey: h56SAJavNkHTN4ocFsQui3Jx6bwJxt33")
    @GET("fixer/latest")
    suspend fun getPrice(
        @Query("symbols") symbols: String,
        @Query("base") base: String,
    ): Response<PriceResponse>

    @Headers("apikey: h56SAJavNkHTN4ocFsQui3Jx6bwJxt33")
    @GET("fixer/symbols")
    suspend fun getCurrency(): Response<CurrencyResponse>
}