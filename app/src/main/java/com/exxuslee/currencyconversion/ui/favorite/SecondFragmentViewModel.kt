package com.exxuslee.currencyconversion.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.collection.ArrayMap
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.exxuslee.currencyconversion.R
import com.exxuslee.currencyconversion.ui.price.FirstFragment
import com.exxuslee.currencyconversion.utils.asLiveData
import com.exxuslee.domain.models.Symbols
import com.exxuslee.domain.usecases.CurrenciesUseCase
import com.exxuslee.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragmentViewModel(private val currenciesUseCase: CurrenciesUseCase.Base) :
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
                withContext(Dispatchers.IO) { currenciesUseCase.load(true) }) {
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
                withContext(Dispatchers.IO) { currenciesUseCase.load(false) }) {
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
            _symbols.value?.symbols ?: ArrayMap(),
            _symbols.value?.favorite ?: ArrayMap(),
            symbols.value?.symbols?.valueAt(num) ?: "EUR"))
        Log.d(FirstFragment.TAG, "position $num")

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                currenciesUseCase.save(Symbols(
                    symbols.value?.symbols ?: ArrayMap(),
                    symbols.value?.favorite ?: ArrayMap(),
                    symbols.value?.symbols?.valueAt(num) ?: "EUR"))
            }
        }
    }

    fun save (fragment: Fragment) {
        val bundle = Bundle()
        bundle.putString("base", symbols.value?.base)
        bundle.putSerializable("symbols", _symbols.value)
        findNavController(fragment).navigate(R.id.FirstFragment, bundle)
    }

    fun check(pos: Int, check: Boolean) {
        _symbols.value?.favorite?.put(pos.toString(), check)
    }
}