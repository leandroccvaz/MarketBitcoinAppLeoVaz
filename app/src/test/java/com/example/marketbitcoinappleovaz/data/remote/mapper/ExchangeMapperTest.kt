package com.example.marketbitcoinappleovaz.data.remote.mapper

import com.example.marketbitcoinappleovaz.data.remote.dto.ExchangeDto
import com.example.marketbitcoinappleovaz.getExchangesDto
import io.kotlintest.shouldBe
import org.junit.Test

class ExchangeMapperTest {

    @Test
    fun `toExchange should map ExchangeDto to Exchange correctly`() {
        // Given
        val dto = getExchangesDto()

        // When
        val result = dto.toExchange()

        // Then
        result.volume1dayUsd shouldBe dto.volume1dayUsd
        result.exchangeId shouldBe dto.exchangeId
        result.name shouldBe dto.name
    }

    @Test
    fun `toExchange should handle null values correctly`() {
        // Given
        val dto = ExchangeDto(
            volume1dayUsd = null,
            exchangeId = null,
            name = null
        )

        // When
        val result = dto.toExchange()

        // Then
        result.volume1dayUsd shouldBe 0.0
        result.exchangeId shouldBe ""
        result.name shouldBe ""
    }
}