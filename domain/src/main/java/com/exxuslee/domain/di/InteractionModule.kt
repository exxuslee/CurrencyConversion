package com.exxuslee.domain.di

import com.exxuslee.domain.usecases.GetCurrenciesUseCase
import com.exxuslee.domain.usecases.GetPriceUseCase
import org.koin.dsl.module

/**
 * Created by Exxus Lee on 23/07/2020.
 */

val interactionModule = module {
    factory<GetPriceUseCase.Base> { GetPriceUseCase.Base(get()) }
    factory<GetCurrenciesUseCase.Base> { GetCurrenciesUseCase.Base(get()) }
}