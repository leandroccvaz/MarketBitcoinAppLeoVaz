package com.example.marketbitcoinappleovaz.di

import com.example.marketbitcoinappleovaz.data.remote.ApiKeyInterceptor
import com.example.marketbitcoinappleovaz.data.remote.ExchangeMarketBitcoinApi
import com.example.marketbitcoinappleovaz.data.repository.CoinRepositoryImpl
import com.example.marketbitcoinappleovaz.domain.repository.ExchangeRepository
import com.example.marketbitcoinappleovaz.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinMarketBitcoinApi(): ExchangeMarketBitcoinApi {
        return Retrofit.Builder()
            .baseUrl(Constants.Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
            .create(ExchangeMarketBitcoinApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: ExchangeMarketBitcoinApi): ExchangeRepository {
        return CoinRepositoryImpl(api)
    }
}