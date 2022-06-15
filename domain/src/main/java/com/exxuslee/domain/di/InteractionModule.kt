package com.exxuslee.domain.di

import com.exxuslee.domain.usecases.CurrenciesUseCase
import com.exxuslee.domain.usecases.GetPriceUseCase
import org.koin.dsl.module

/**
 * Created by Exxus Lee on 23/07/2020.
 */

val interactionModule = module {
    factory<GetPriceUseCase.Base> { GetPriceUseCase.Base(get()) }
    factory<CurrenciesUseCase.Base> { CurrenciesUseCase.Base(get()) }
}