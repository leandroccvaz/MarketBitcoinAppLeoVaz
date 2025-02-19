package com.example.marketbitcoinappleovaz

import com.example.marketbitcoinappleovaz.data.remote.dto.ExchangeDetailsDto
import com.example.marketbitcoinappleovaz.data.remote.dto.ExchangeDto
import com.example.marketbitcoinappleovaz.data.remote.dto.ExchangeIconDto
import com.example.marketbitcoinappleovaz.data.remote.mapper.toExchange
import com.example.marketbitcoinappleovaz.data.remote.mapper.toExchangeDetails
import com.example.marketbitcoinappleovaz.data.remote.mapper.toExchangeIcon

fun getExchangeDetailsDto() = ExchangeDetailsDto(
    exchangeId = "1",
    name = "Bitcoin",
    rank = 1.0,
    volume1dayUsd = 1.0,
    volume1hrsUsd = 1.0,
    volume1mthUsd = 1.0,
    website = "https://bitcoin.com"
)

fun getExchangesDto() = ExchangeDto(
    exchangeId = "1",
    name = "Bitcoin",
    volume1dayUsd = 1.0,
)

fun getExchangeIconDto() = ExchangeIconDto(
    exchangeId = "1",
    assetId = "assetId",
    url = "url"
)

fun getExchangeDetails() = getExchangeDetailsDto().toExchangeDetails()

fun getExchanges() = getExchangesDto().toExchange()

fun getExchangeIcon() = getExchangeIconDto().toExchangeIcon()
