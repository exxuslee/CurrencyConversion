package com.exxuslee.currencyconversion.di

import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val presentationModule = module {
    viewModel { (get()) }
}