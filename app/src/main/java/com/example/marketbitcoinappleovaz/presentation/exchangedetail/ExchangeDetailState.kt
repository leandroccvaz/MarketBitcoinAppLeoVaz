package com.example.marketbitcoinappleovaz.presentation.exchangedetail

import com.example.marketbitcoinappleovaz.domain.model.ExchangeDetails

data class ExchangeDetailState(
    val exchangeDetails: ExchangeDetails? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)