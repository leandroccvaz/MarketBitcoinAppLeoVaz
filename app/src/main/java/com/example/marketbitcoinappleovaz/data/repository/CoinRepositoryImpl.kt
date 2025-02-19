package com.example.marketbitcoinappleovaz.data.repository

import com.example.marketbitcoinappleovaz.data.remote.ExchangeMarketBitcoinApi
import com.example.marketbitcoinappleovaz.data.remote.dto.ExchangeDetailsDto
import com.example.marketbitcoinappleovaz.data.remote.dto.ExchangeDto
import com.example.marketbitcoinappleovaz.data.remote.dto.ExchangeIconDto
import com.example.marketbitcoinappleovaz.domain.repository.ExchangeRepository

class CoinRepositoryImpl (
    private val api: ExchangeMarketBitcoinApi
) : ExchangeRepository {
    override suspend fun getExchanges(): List<ExchangeDto> = api.getExchanges()

    override suspend fun getExchangeById(exchangeId: String): List<ExchangeDetailsDto> = api.getExchangeById(exchangeId)

    override suspend fun getExchangeIcon(iconSize: Int): List<ExchangeIconDto> = api.getExchangeIcon(iconSize)
}