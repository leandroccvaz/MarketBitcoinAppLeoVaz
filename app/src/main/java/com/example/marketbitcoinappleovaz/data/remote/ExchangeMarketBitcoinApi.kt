package com.example.marketbitcoinappleovaz.data.remote

import com.example.marketbitcoinappleovaz.data.remote.dto.ExchangeDetailsDto
import com.example.marketbitcoinappleovaz.data.remote.dto.ExchangeDto
import com.example.marketbitcoinappleovaz.data.remote.dto.ExchangeIconDto
import com.example.marketbitcoinappleovaz.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeMarketBitcoinApi {
    @GET(Constants.Api.LIST_OF_EXCHANGES_URL)
    suspend fun getExchanges(): List<ExchangeDto>

    @GET(Constants.Api.EXCHANGE_DETAIL_URL)
    suspend fun getExchangeById(@Path(Constants.PARAM_EXCHANGE_ID) exchangeId: String): List<ExchangeDetailsDto>

    @GET(Constants.Api.EXCHANGE_ICONS_URL)
    suspend fun getExchangeIcon(@Path(Constants.PARAM_SIZE) iconSize: Int): List<ExchangeIconDto>
}