package com.example.marketbitcoinappleovaz.presentation.exchangeslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.marketbitcoinappleovaz.domain.usecase.getexchangelistwithicon.GetExchangeListWithIconUseCase
import com.example.marketbitcoinappleovaz.getExchanges
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
class ExchangeListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getExchangeListWithIconUseCase: GetExchangeListWithIconUseCase
    private lateinit var viewModel: ExchangeListViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getExchangeListWithIconUseCase = mockk()
    }

    @Test
    fun `getExchangesWithIcons should emit loading, success and update state`() = runTest(testDispatcher) {
        val exchangeList = listOf(
            getExchanges()
        )

        every { getExchangeListWithIconUseCase() } returns (
                flow {
                    emit(Resource.Loading())
                    emit(Resource.Success(exchangeList))
                }
                )

        viewModel = ExchangeListViewModel(getExchangeListWithIconUseCase)
        viewModel.state.test {
            awaitItem().isLoading shouldBe false
            awaitItem().isLoading shouldBe true
            awaitItem().exchangeList shouldBe exchangeList
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getExchangesWithIcons should emit loading, error and update state`() = runTest(testDispatcher) {
        val errorMessage = UNKNOWN_ERROR

        every { getExchangeListWithIconUseCase() } returns (
                flow {
                    emit(Resource.Loading())
                    emit(Resource.Error(errorMessage))
                }
                )

        viewModel = ExchangeListViewModel(getExchangeListWithIconUseCase)
        viewModel.state.test {
            awaitItem().isLoading shouldBe false
            awaitItem().isLoading shouldBe true
            awaitItem().error shouldBe errorMessage
            cancelAndIgnoreRemainingEvents()
        }
    }
}