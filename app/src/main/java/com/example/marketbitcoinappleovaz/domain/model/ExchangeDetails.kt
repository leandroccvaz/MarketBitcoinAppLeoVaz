package com.example.marketbitcoinappleovaz.domain.model

data class ExchangeDetails(
    val exchangeId: String,
    val name: String,
    val rank: Double,
    val volume1dayUsd: Double,
    val volume1hrsUsd: Double,
    val volume1mthUsd: Double,
    val website: String
)
