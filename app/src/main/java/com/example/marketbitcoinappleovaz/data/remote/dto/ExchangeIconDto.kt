package com.example.marketbitcoinappleovaz.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ExchangeIconDto(
    @SerializedName("asset_id")
    val assetId: String?,
    @SerializedName("exchange_id")
    val exchangeId: String?,
    @SerializedName("url")
    val url: String?
)