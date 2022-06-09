package com.exxuslee.domain.usecases

import com.exxuslee.domain.repositories.PriceRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PriceUseCaseTest {

    // region helper fields
    @Mock
    private lateinit var priceRepository: PriceRepository
    private lateinit var getPrice: GetPriceUseCase.Base
    private val getBase = "false"
    private val getSymbols = "12345"
    private val getFromRemote: Boolean = false
    // endregion helper fields

    @Before
    fun setUp() {
        getPrice = GetPriceUseCase.Base(priceRepository)
    }

    @Test
    fun getPriceUseCase_calls_PriceRepository() {
        runBlockingTest {
            getPrice(getBase, getSymbols, getFromRemote)
            Mockito.verify(priceRepository).getPrice(getBase, getSymbols, getFromRemote)
        }
    }
}