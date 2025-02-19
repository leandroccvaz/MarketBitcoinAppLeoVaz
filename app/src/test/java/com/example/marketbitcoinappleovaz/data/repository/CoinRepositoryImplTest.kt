package com.example.marketbitcoinappleovaz.data.repository

import com.example.marketbitcoinappleovaz.data.remote.ExchangeMarketBitcoinApi
import com.example.marketbitcoinappleovaz.getExchangeDetailsDto
import com.example.marketbitcoinappleovaz.getExchangeIconDto
import com.example.marketbitcoinappleovaz.getExchangesDto
import io.kotlintest.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CoinRepositoryImplTest {

    private lateinit var repository: CoinRepositoryImpl

    @MockK
    private lateinit var api: ExchangeMarketBitcoinApi

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        repository = CoinRepositoryImpl(api)
    }

    @Test
    fun `should return list of exchanges`() = runBlocking {
        val exchangeList = listOf(getExchangesDto())
        coEvery { api.getExchanges() } returns exchangeList

        val result = repository.getExchanges()

        result shouldBe exchangeList
        coVerify(exactly = 1) { api.getExchanges() }
    }

    @Test
    fun `should return exchange by id`() = runBlocking {
        val exchange = listOf(getExchangeDetailsDto())
        coEvery { api.getExchangeById("1") } returns exchange

        val result = repository.getExchangeById("1")

        result shouldBe exchange
        coVerify(exactly = 1) { api.getExchangeById("1") }
    }

    @Test
    fun `should return exchange icons`() = runBlocking {
        val exchangeIcon = listOf(getExchangeIconDto())
        coEvery { api.getExchangeIcon(32) } returns exchangeIcon

        val result = repository.getExchangeIcon(32)

        result shouldBe exchangeIcon
        coVerify(exactly = 1) { api.getExchangeIcon(32) }
    }
}