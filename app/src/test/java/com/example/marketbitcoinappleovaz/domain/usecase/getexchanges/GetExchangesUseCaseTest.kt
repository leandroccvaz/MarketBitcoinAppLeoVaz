package com.example.marketbitcoinappleovaz.domain.usecase.getexchanges

import app.cash.turbine.test
import com.example.marketbitcoinappleovaz.data.remote.mapper.toExchange
import com.example.marketbitcoinappleovaz.domain.model.Exchange
import com.example.marketbitcoinappleovaz.domain.repository.ExchangeRepository
import com.example.marketbitcoinappleovaz.getExchangesDto
import com.example.marketbitcoinappleovaz.utils.Constants.ApiError.UNKNOWN_ERROR
import com.example.marketbitcoinappleovaz.utils.Resource
import io.kotlintest.matchers.types.shouldBeInstanceOf
import io.kotlintest.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetExchangesUseCaseTest {

    private lateinit var useCase: GetExchangesUseCase

    @MockK
    private lateinit var repository: ExchangeRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        useCase = GetExchangesUseCase(repository)
    }

    @Test
    fun `useCase MUST return list of exchanges WHEN successful`() = runBlocking {
        val exchangeList = listOf(getExchangesDto())
        coEvery { repository.getExchanges() } returns exchangeList

        useCase().test {
            awaitItem().shouldBeInstanceOf<Resource.Loading<List<Exchange>>>()
            awaitItem().apply {
                this.shouldBeInstanceOf<Resource.Success<List<Exchange>>>()
                this.data shouldBe exchangeList.map { it.toExchange() }
            }
            awaitComplete()
        }
    }

    @Test
    fun `useCase MUST return error WHEN failed`() = runBlocking {
        val exception = Exception(UNKNOWN_ERROR)
        coEvery { repository.getExchanges() } throws exception

        useCase().test {
            awaitItem().shouldBeInstanceOf<Resource.Loading<List<Exchange>>>()
            awaitItem().apply {
                this.shouldBeInstanceOf<Resource.Error<List<Exchange>>>()
                this.message shouldBe exception.message
            }
            awaitComplete()
        }
    }
}