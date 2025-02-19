package com.example.marketbitcoinappleovaz.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ExchangeDetailsDto(
    @SerializedName("exchange_id")
    val exchangeId: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("rank")
    val rank: Double?,
    @SerializedName("volume_1day_usd")
    val volume1dayUsd: Double?,
    @SerializedName("volume_1hrs_usd")
    val volume1hrsUsd: Double?,
    @SerializedName("volume_1mth_usd")
    val volume1mthUsd: Double?,
    @SerializedName("website")
    val website: String?
)