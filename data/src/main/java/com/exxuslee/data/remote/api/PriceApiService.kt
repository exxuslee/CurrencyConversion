package com.exxuslee.data.remote.api

import com.exxuslee.data.remote.response.PriceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PriceApiService {
    @GET("symbols={symbols}&base={base}")
    suspend fun getPrice(
        @Path("symbols") symbols: String,
        @Path("base") base: String,
    ): Response<PriceResponse>
}