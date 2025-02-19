package com.example.marketbitcoinappleovaz.data.remote.mapper

import com.example.marketbitcoinappleovaz.data.remote.dto.ExchangeIconDto
import com.example.marketbitcoinappleovaz.getExchangeIconDto
import io.kotlintest.shouldBe
import org.junit.Test

class ExchangeIconMapperTest {

    @Test
    fun `toExchangeIcon should map ExchangeIconDto to ExchangeIcon correctly`() {
        // Given
        val dto = getExchangeIconDto()

        // When
        val result = dto.toExchangeIcon()

        // Then
        result.assetId shouldBe dto.assetId
        result.exchangeId shouldBe dto.exchangeId
        result.url shouldBe dto.url
    }

    @Test
    fun `toExchangeIcon should handle null values correctly`() {
        // Given
        val dto = ExchangeIconDto(
            assetId = null,
            exchangeId = null,
            url = null
        )

        // When
        val result = dto.toExchangeIcon()

        // Then
        result.assetId shouldBe ""
        result.exchangeId shouldBe ""
        result.url shouldBe ""
    }
}