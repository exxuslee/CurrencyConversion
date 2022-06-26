package com.exxuslee.currencyconversion.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.exxuslee.currencyconversion.R
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

    fun radioSelect(pos: Int) {
        val asd: List<Symbols.Symbol> = symbols.value?.symbol!!
        asd.map { it.base = false }
        asd[pos].base = true
        _symbols.postValue(Symbols(asd))

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                symbols.value?.let {
                    currenciesUseCase.saveAll(it)
                }
            }
        }
    }

    fun checkSelect(pos: Int, check: Boolean) {
        val asd: List<Symbols.Symbol> = symbols.value?.symbol!!
        asd[pos].check = check
        _symbols.postValue(Symbols(asd))
    }

    fun save(fragment: Fragment) {
        val bundle = Bundle()
        var base = "EUR"
        val check = ""
        symbols.value?.symbol?.forEach {
            if (it.base) base = it.name
            if (it.check) check.plus(it.xxx + ", ")
        }
        bundle.putString("base", base)
        bundle.putSerializable("symbols", check)
        findNavController(fragment).navigate(R.id.FirstFragment, bundle)
    }
}