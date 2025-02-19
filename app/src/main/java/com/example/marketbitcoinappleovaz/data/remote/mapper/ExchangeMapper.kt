package com.example.marketbitcoinappleovaz.data.remote.mapper

import com.example.marketbitcoinappleovaz.data.remote.dto.ExchangeDto
import com.example.marketbitcoinappleovaz.domain.model.Exchange

fun ExchangeDto.toExchange(): Exchange {
    return Exchange(
        volume1dayUsd = this.volume1dayUsd ?: 0.0,
        exchangeId = this.exchangeId.orEmpty(),
        name = this.name.orEmpty(),
    )
}
