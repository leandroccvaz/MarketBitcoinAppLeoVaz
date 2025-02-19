package com.example.marketbitcoinappleovaz.domain.repository

import com.example.marketbitcoinappleovaz.data.remote.dto.ExchangeDetailsDto
import com.example.marketbitcoinappleovaz.data.remote.dto.ExchangeDto
import com.example.marketbitcoinappleovaz.data.remote.dto.ExchangeIconDto

interface ExchangeRepository {

    suspend fun getExchanges(): List<ExchangeDto>
    suspend fun getExchangeById(exchangeId: String): List<ExchangeDetailsDto>
    suspend fun getExchangeIcon(iconSize: Int): List<ExchangeIconDto>

}