package com.example.marketbitcoinappleovaz.presentation.exchangedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.marketbitcoinappleovaz.domain.usecase.getexchange.GetExchangeUseCase
import com.example.marketbitcoinappleovaz.getExchangeDetails
import com.example.marketbitcoinappleovaz.utils.Constants
import com.example.marketbitcoinappleovaz.utils.Constants.ApiError.UNKNOWN_ERROR
import com.example.marketbitcoinappleovaz.utils.Resource
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ExchangeDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getExchangeUseCase: GetExchangeUseCase
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var viewModel: ExchangeDetailViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getExchangeUseCase = mockk()
        savedStateHandle = SavedStateHandle(mapOf(Constants.PARAM_EXCHANGE_ID to "binance"))
    }

    @Test
    fun `getExchange should emit loading, success and update state`() = runTest(testDispatcher) {
        val exchangeDetails = getExchangeDetails()

        every { getExchangeUseCase("binance") } returns (
                flow {
                    emit(Resource.Loading())
                    emit(Resource.Success(exchangeDetails))
                }
                )
        viewModel = ExchangeDetailViewModel(getExchangeUseCase, savedStateHandle)
        viewModel.state.test {
            awaitItem().isLoading shouldBe false
            awaitItem().isLoading shouldBe true
            awaitItem().exchangeDetails shouldBe exchangeDetails
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getExchange should emit loading, error and update state`() = runTest(testDispatcher) {
        val errorMessage = UNKNOWN_ERROR

        every { getExchangeUseCase("binance") } returns (
                flow {
                    emit(Resource.Loading())
                    emit(Resource.Error(errorMessage))
                }
                )

        viewModel = ExchangeDetailViewModel(getExchangeUseCase, savedStateHandle)
        viewModel.state.test {
            awaitItem().isLoading shouldBe false
            awaitItem().isLoading shouldBe true
            awaitItem().error shouldBe errorMessage
            cancelAndIgnoreRemainingEvents()
        }
    }
}