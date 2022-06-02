package com.exxuslee.currencyconversion.di

import com.exxuslee.currencyconversion.ui.FistFragmentViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val presentationModule = module {
    viewModel { FistFragmentViewModel(get()) }
}