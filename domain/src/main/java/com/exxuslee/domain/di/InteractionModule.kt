package com.exxuslee.domain.di

import org.koin.dsl.module

/**
 * Created by Mayokun Adeniyi on 23/07/2020.
 */

val interactionModule = module {
    factory<GetCardInfoUseCase> { GetCardInfoUseCaseImpl(get()) }
}