package com.exxuslee.domain.di

import com.exxuslee.domain.usecases.GetCardInfoUseCase
import org.koin.dsl.module

/**
 * Created by Exxus Lee on 23/07/2020.
 */

val interactionModule = module {
    factory<GetCardInfoUseCase> { GetCardInfoUseCase.Base(get()) }
}