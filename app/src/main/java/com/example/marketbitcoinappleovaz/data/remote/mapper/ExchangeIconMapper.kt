package com.example.marketbitcoinappleovaz.data.remote.mapper

import com.example.marketbitcoinappleovaz.data.remote.dto.ExchangeIconDto
import com.example.marketbitcoinappleovaz.domain.model.ExchangeIcon

fun ExchangeIconDto.toExchangeIcon(): ExchangeIcon {
    return ExchangeIcon(
        assetId = this.assetId.orEmpty(),
        exchangeId = this.exchangeId.orEmpty(),
        url = this.url.orEmpty(),
    )
}
