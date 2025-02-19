package com.example.marketbitcoinappleovaz.data.remote.mapper

import com.example.marketbitcoinappleovaz.data.remote.dto.ExchangeDetailsDto
import com.example.marketbitcoinappleovaz.getExchangeDetailsDto
import io.kotlintest.shouldBe
import org.junit.Test

class ExchangeDetailsMapperTest {

    @Test
    fun `toExchangeDetails should map ExchangeDetailsDto to ExchangeDetails correctly`() {
        // Given
        val dto = getExchangeDetailsDto()

        // When
        val result = dto.toExchangeDetails()

        // Then
        result.exchangeId shouldBe dto.exchangeId
        result.name shouldBe dto.name
        result.rank shouldBe dto.rank
        result.volume1dayUsd shouldBe dto.volume1dayUsd
        result.volume1hrsUsd shouldBe dto.volume1hrsUsd
        result.volume1mthUsd shouldBe dto.volume1mthUsd
        result.website shouldBe dto.website
    }

    @Test
    fun `toExchangeDetails should handle null values correctly`() {
        // Given
        val dto = ExchangeDetailsDto(
            exchangeId = null,
            name = null,
            rank = null,
            volume1dayUsd = null,
            volume1hrsUsd = null,
            volume1mthUsd = null,
            website = null
        )

        // When
        val result = dto.toExchangeDetails()

        // Then
        result.exchangeId shouldBe ""
        result.name shouldBe ""
        result.rank shouldBe 0.0
        result.volume1dayUsd shouldBe 0.0
        result.volume1hrsUsd shouldBe 0.0
        result.volume1mthUsd shouldBe 0.0
        result.website shouldBe ""
    }
}