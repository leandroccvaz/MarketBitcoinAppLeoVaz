package com.example.marketbitcoinappleovaz.utils

object Constants {
    const val PARAM_EXCHANGE_ID = "exchange_id"
    const val PARAM_EXCHANGE_ICON = "exchange_icon"
    const val PARAM_ICON_SIZE = 48
    const val PARAM_SIZE = "size"
    const val COIN_API = "X-CoinAPI-Key"
    object Route {
        const val COIN_LIST_SCREEN = "coin_list_screen"
        const val COIN_LIST_DETAIL_SCREEN = "coin_list_detail_screen"
    }
    object Api {
        const val BASE_URL = "https://rest.coinapi.io/v1/"
        const val LIST_OF_EXCHANGES_URL = "exchanges"
        const val EXCHANGE_DETAIL_URL = "exchanges/{${PARAM_EXCHANGE_ID}}"
        const val EXCHANGE_ICONS_URL = "exchanges/icons/{${PARAM_SIZE}}"
    }
    object ComposeId {
        const val EXCHANGE_LIST_IMAGE_ID = "exchange_list_image_id"
        const val EXCHANGE_LIST_TITLE_ID = "exchange_list_title_id"
        const val EXCHANGE_LIST_NAME_ID = "exchange_list_name_id"
        const val EXCHANGE_LIST_VOLUME_ID = "exchange_list_volume_id"
        const val EXCHANGE_LIST_CLICK_ID = "exchange_list_click_id"
        const val EXCHANGE_DETAIL_IMAGE_ID = "exchange_detail_image_id"
        const val EXCHANGE_DETAIL_TITLE_ID = "exchange_detail_title_id"
        const val EXCHANGE_DETAIL_VOLUME_IN_HOUR_TITLE_ID = "exchange_detail_volume_in_hour_id"
        const val EXCHANGE_DETAIL_VOLUME_IN_DAY_TITLE_ID = "exchange_detail_volume_in_day_id"
        const val EXCHANGE_DETAIL_VOLUME_IN_MONTH_TITLE_ID = "exchange_detail_volume_in_month_id"
        const val EXCHANGE_DETAIL_VOLUME_IN_HOUR_VALUE_ID = "exchange_detail_volume_in_hour_value_id"
        const val EXCHANGE_DETAIL_VOLUME_IN_DAY_VALUE_ID = "exchange_detail_volume_in_day_value_id"
        const val EXCHANGE_DETAIL_VOLUME_IN_MONTH_VALUE_ID = "exchange_detail_volume_in_month_value_id"
        const val EXCHANGE_DETAIL_RANK_ID = "exchange_detail_rank_id"
        const val EXCHANGE_DETAIL_WEBSITE_ID = "exchange_detail_website_id"
        const val EXCHANGE_ERROR_ID = "exchange_error_id"
        const val EXCHANGE_LOADING_ID = "exchange_loading_id"
    }
    object ApiError {
        const val UNKNOWN_ERROR = "An unexpected error occurred"
        const val INTERNET_CONNECTION_ERROR = "Check your internet connection"
    }
}