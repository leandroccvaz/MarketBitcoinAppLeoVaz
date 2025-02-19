package com.example.marketbitcoinappleovaz.utils

import com.example.marketbitcoinappleovaz.utils.Constants.ApiError.INTERNET_CONNECTION_ERROR
import com.example.marketbitcoinappleovaz.utils.Constants.ApiError.UNKNOWN_ERROR
import java.io.IOException

fun <T> Throwable.toResourceError(): Resource.Error<T> {
    val message = when (this) {
        is IOException -> INTERNET_CONNECTION_ERROR
        else -> UNKNOWN_ERROR
    }
    return Resource.Error(message)
}
