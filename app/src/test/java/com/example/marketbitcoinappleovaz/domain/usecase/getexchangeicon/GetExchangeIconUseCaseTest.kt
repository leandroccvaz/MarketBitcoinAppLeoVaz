package com.example.marketbitcoinappleovaz.domain.usecase.getexchangeicon

import app.cash.turbine.test
import com.example.marketbitcoinappleovaz.data.remote.mapper.toExchangeIcon
import com.example.marketbitcoinappleovaz.domain.model.ExchangeIcon
import com.example.marketbitcoinappleovaz.domain.repository.ExchangeRepository
import com.example.marketbitcoinappleovaz.getExchangeIconDto
import com.example.marketbitcoinappleovaz.utils.Constants.ApiError.UNKNOWN_ERROR
import com.example.marketbitcoinappleovaz.utils.Resource
import io.kotlintest.matchers.types.shouldBeInstanceOf
import io.kotlintest.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetExchangeIconUseCaseTest {

    private val repository: ExchangeRepository = mockk(relaxed = true)
    private lateinit var useCase: GetExchangeIconUseCase

    @Before
    fun setUp() {
        useCase = GetExchangeIconUseCase(repository)
    }


    @Test
    fun `when repository returns icons then should return success with icons`() = runBlocking {
        // Given
        val iconSize = 64
        val exchangeIcons = listOf(
            getExchangeIconDto(),
            getExchangeIconDto()
        )
        coEvery { repository.getExchangeIcon(iconSize) } returns exchangeIcons

        // When
        useCase(iconSize).test {
            awaitItem().shouldBeInstanceOf<Resource.Loading<List<ExchangeIcon>>>()
            awaitItem().apply {
                this.shouldBeInstanceOf<Resource.Success<List<ExchangeIcon>>>()
                this.data shouldBe exchangeIcons.map { it.toExchangeIcon() }
            }
            awaitComplete()
        }
    }

    @Test
    fun `when repository throws exception then should return error`() = runBlocking {
        // Given
        val iconSize = 64
        val exception = RuntimeException(UNKNOWN_ERROR)
        coEvery { repository.getExchangeIcon(iconSize) } throws exception

        // When
        useCase(iconSize).test {
            awaitItem().shouldBeInstanceOf<Resource.Loading<List<ExchangeIcon>>>()
            awaitItem().apply {
                this.shouldBeInstanceOf<Resource.Error<List<ExchangeIcon>>>()
                this.message shouldBe exception.message
            }
            awaitComplete()
        }
    }
}