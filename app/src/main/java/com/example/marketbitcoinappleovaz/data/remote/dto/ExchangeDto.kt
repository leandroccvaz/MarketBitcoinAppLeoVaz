package com.example.marketbitcoinappleovaz.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ExchangeDto(
    @SerializedName("volume_1day_usd") val volume1dayUsd: Double?,
    @SerializedName("exchange_id") val exchangeId: String?,
    @SerializedName("name") val name: String?,
)