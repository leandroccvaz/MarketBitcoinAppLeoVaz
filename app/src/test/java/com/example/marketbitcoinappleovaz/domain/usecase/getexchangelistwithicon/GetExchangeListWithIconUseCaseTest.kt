package com.example.marketbitcoinappleovaz.domain.usecase.getexchangelistwithicon

import app.cash.turbine.test
import com.example.marketbitcoinappleovaz.domain.model.Exchange
import com.example.marketbitcoinappleovaz.domain.usecase.getexchangeicon.GetExchangeIconUseCase
import com.example.marketbitcoinappleovaz.domain.usecase.getexchanges.GetExchangesUseCase
import com.example.marketbitcoinappleovaz.getExchangeIcon
import com.example.marketbitcoinappleovaz.getExchanges
import com.example.marketbitcoinappleovaz.utils.Constants
import com.example.marketbitcoinappleovaz.utils.Constants.ApiError.UNKNOWN_ERROR
import com.example.marketbitcoinappleovaz.utils.Resource
import io.kotlintest.matchers.types.shouldBeInstanceOf
import io.kotlintest.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetExchangeListWithIconUseCaseTest {

    @MockK
    private lateinit var getExchangeIconUseCase: GetExchangeIconUseCase

    @MockK
    private lateinit var getExchangesUseCase: GetExchangesUseCase

    private lateinit var useCase: GetExchangeListWithIconUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        useCase = GetExchangeListWithIconUseCase(getExchangeIconUseCase, getExchangesUseCase)
    }

    @Test
    fun `when both use cases return success then should return success with exchanges and icons`() = runBlocking {
        // Given
        val exchanges = listOf(
            getExchanges(),
            getExchanges()
        )
        val exchangeIcons = listOf(
            getExchangeIcon(),
            getExchangeIcon()
        )

        coEvery { getExchangesUseCase() } returns flowOf(Resource.Success(exchanges))
        coEvery { getExchangeIconUseCase(Constants.PARAM_ICON_SIZE) } returns flowOf(Resource.Success(exchangeIcons))

        // When
        useCase().test {
            awaitItem().shouldBeInstanceOf<Resource.Loading<List<Exchange>>>()
            awaitItem().apply {
                this.shouldBeInstanceOf<Resource.Success<List<Exchange>>>()
                this.data shouldBe exchanges.map { exchange ->
                    exchange.copy(iconUrl = exchangeIcons.find { it.exchangeId == exchange.exchangeId }?.url)
                }
            }
            awaitComplete()
        }
    }

    @Test
    fun `when exchanges use case returns error then should return error`() = runBlocking {
        // Given
        val errorMessage = UNKNOWN_ERROR
        coEvery { getExchangesUseCase() } returns flowOf(Resource.Error(errorMessage))
        coEvery { getExchangeIconUseCase(Constants.PARAM_ICON_SIZE) } returns flowOf(Resource.Success(emptyList()))

        // When
        useCase().test {
            awaitItem().shouldBeInstanceOf<Resource.Loading<List<Exchange>>>()
            awaitItem().apply {
                this.shouldBeInstanceOf<Resource.Error<List<Exchange>>>()
                this.message shouldBe errorMessage
            }
            awaitComplete()
        }
    }

    @Test
    fun `when icons use case returns error then should return error`() = runBlocking {
        // Given
        val errorMessage = UNKNOWN_ERROR
        coEvery { getExchangesUseCase() } returns flowOf(Resource.Success(emptyList()))
        coEvery { getExchangeIconUseCase(Constants.PARAM_ICON_SIZE) } returns flowOf(Resource.Error(errorMessage))

        // When
        useCase().test {
            awaitItem().shouldBeInstanceOf<Resource.Loading<List<Exchange>>>()
            awaitItem().apply {
                this.shouldBeInstanceOf<Resource.Error<List<Exchange>>>()
                this.message shouldBe errorMessage
            }
            awaitComplete()
        }
    }
}