package com.exxuslee.currencyconversion.ui.price

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exxuslee.currencyconversion.utils.asLiveData
import com.exxuslee.domain.models.Price
import com.exxuslee.domain.usecases.GetPriceUseCase
import com.exxuslee.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FistFragmentViewModel(private val getCardInfoUseCase: GetPriceUseCase.Base) : ViewModel() {
    private val _price = MutableLiveData<Price?>()
    val price = _price.asLiveData()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    private val _dataFetchState = MutableLiveData<Boolean>()
    val dataFetchState = _dataFetchState.asLiveData()

    private fun remotePrice() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { getCardInfoUseCase("EUR", "EUR,USD,GBP", true) }) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    if (result.data != null) {
                        _dataFetchState.postValue(true)
                        _price.postValue(result.data)
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

    fun localPrice() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { getCardInfoUseCase("EUR", "EUR,USD,GBP", false) }) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    if (result.data != null) {
                        _dataFetchState.postValue(true)
                        _price.postValue(result.data)
                    } else {
                        remotePrice()
                    }
                }
                else -> {}
            }
        }
    }
}