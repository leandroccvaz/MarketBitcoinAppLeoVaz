package com.example.marketbitcoinappleovaz.domain.model

data class Exchange(
    val volume1dayUsd: Double,
    val exchangeId: String,
    val name: String,
    var iconUrl: String? = null
)
