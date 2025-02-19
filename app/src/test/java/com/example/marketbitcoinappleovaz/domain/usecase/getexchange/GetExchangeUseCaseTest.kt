package com.example.marketbitcoinappleovaz.domain.usecase.getexchange

import app.cash.turbine.test
import com.example.marketbitcoinappleovaz.data.remote.mapper.toExchangeDetails
import com.example.marketbitcoinappleovaz.domain.model.ExchangeDetails
import com.example.marketbitcoinappleovaz.domain.repository.ExchangeRepository
import com.example.marketbitcoinappleovaz.getExchangeDetailsDto
import com.example.marketbitcoinappleovaz.utils.Constants.ApiError.UNKNOWN_ERROR
import com.example.marketbitcoinappleovaz.utils.Resource
import io.kotlintest.matchers.types.shouldBeInstanceOf
import io.kotlintest.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetExchangeUseCaseTest {

    private lateinit var useCase: GetExchangeUseCase

    @MockK
    private lateinit var repository: ExchangeRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        useCase = GetExchangeUseCase(repository)
    }

    @Test
    fun `useCase MUST return exchange WHEN successful`() = runBlocking {
        val exchange = getExchangeDetailsDto()
        coEvery { repository.getExchangeById("1") } returns listOf(exchange)

        useCase("1").test {
            awaitItem().shouldBeInstanceOf<Resource.Loading<ExchangeDetails>>()
            awaitItem().apply {
                this.shouldBeInstanceOf<Resource.Success<ExchangeDetails>>()
                this.data shouldBe exchange.toExchangeDetails()
            }
            awaitComplete()
        }
    }

    @Test
    fun `useCase MUST return error WHEN failed`() = runBlocking {
        val exception = Exception(UNKNOWN_ERROR)
        coEvery { repository.getExchangeById("1") } throws exception

        useCase("1").test {
            awaitItem().shouldBeInstanceOf<Resource.Loading<ExchangeDetails>>()
            awaitItem().apply {
                this.shouldBeInstanceOf<Resource.Error<ExchangeDetails>>()
                this.message shouldBe exception.message
            }
            awaitComplete()
        }
    }
}