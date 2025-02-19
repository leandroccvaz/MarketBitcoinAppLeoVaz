package com.example.marketbitcoinappleovaz.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import com.example.marketbitcoinappleovaz.BuildConfig
import com.example.marketbitcoinappleovaz.utils.Constants

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(Constants.COIN_API, BuildConfig.COIN_API_KEY)
            .build()
        return chain.proceed(request)
    }
}