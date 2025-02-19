package com.example.marketbitcoinappleovaz.presentation.exchangeslist

import com.example.marketbitcoinappleovaz.domain.model.Exchange

data class ExchangeListState(
    val exchangeList: List<Exchange> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
