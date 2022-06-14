package com.exxuslee.data.di

import com.exxuslee.data.repositories.PriceRepositoryImpl
import com.exxuslee.domain.repositories.PriceRepository
import org.koin.dsl.module

/**
 * Created by Exxus Lee on 23/07/2020.
 */

val repositoryModule = module {
    factory<PriceRepository> { PriceRepositoryImpl(get(), get(), get()) }
}