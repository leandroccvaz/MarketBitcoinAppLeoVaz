package com.example.marketbitcoinappleovaz.data.remote.mapper

import com.example.marketbitcoinappleovaz.data.remote.dto.ExchangeDetailsDto
import com.example.marketbitcoinappleovaz.domain.model.ExchangeDetails

fun ExchangeDetailsDto.toExchangeDetails(): ExchangeDetails {
    return ExchangeDetails(
        exchangeId = this.exchangeId.orEmpty(),
        name = this.name.orEmpty(),
        rank = this.rank ?: 0.0,
        volume1dayUsd = this.volume1dayUsd ?: 0.0,
        volume1hrsUsd = this.volume1hrsUsd ?: 0.0,
        volume1mthUsd = this.volume1mthUsd ?: 0.0,
        website = this.website.orEmpty()
    )
}