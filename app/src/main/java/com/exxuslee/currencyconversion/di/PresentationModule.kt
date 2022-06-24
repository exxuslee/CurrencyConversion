package com.exxuslee.currencyconversion.di

import com.exxuslee.currencyconversion.ui.favorite.SecondFragmentViewModel
import com.exxuslee.currencyconversion.ui.price.FistFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { FistFragmentViewModel(get()) }
    viewModel { SecondFragmentViewModel(get()) }
}