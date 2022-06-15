package com.exxuslee.currencyconversion.ui.favorite

import android.util.Log
import androidx.collection.ArrayMap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exxuslee.currencyconversion.ui.price.FirstFragment
import com.exxuslee.currencyconversion.utils.asLiveData
import com.exxuslee.domain.models.Symbols
import com.exxuslee.domain.usecases.GetCurrenciesUseCase
import com.exxuslee.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.ext.scope

class SecondFragmentViewModel(private val getCurrenciesUseCase: GetCurrenciesUseCase.Base) :
    ViewModel() {
    private val _symbols = MutableLiveData<Symbols?>()
    val symbols = _symbols.asLiveData()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    private val _dataFetchState = MutableLiveData<Boolean>()
    val dataFetchState = _dataFetchState.asLiveData()

    private fun remoteCurrency() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { getCurrenciesUseCase(true) }) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    if (result.data != null) {
                        _dataFetchState.postValue(true)
                        _symbols.postValue(result.data)
                    } else {
                        _dataFetchState.postValue(false)
                    }
                }

                is Result.Error -> {
                    _isLoading.postValue(false)
                    _dataFetchState.postValue(false)
                }
                else -> {}
            }
        }
    }

    fun localCurrency() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { getCurrenciesUseCase(false) }) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    if (result.data != null) {
                        _dataFetchState.postValue(true)
                        _symbols.postValue(result.data)
                    } else {
                        remoteCurrency()
                    }
                }
                else -> {}
            }
        }
    }

    fun radioSelect(num: Int) {
        _symbols.postValue(Symbols(
            symbols.value?.symbols?: ArrayMap(),
            symbols.value?.favorite?: ArrayMap(),
            "ALL"))
        Log.d(FirstFragment.TAG, "position $num")
    }
}