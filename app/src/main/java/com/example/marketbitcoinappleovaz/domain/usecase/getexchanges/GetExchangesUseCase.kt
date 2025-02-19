package com.example.marketbitcoinappleovaz.domain.usecase.getexchanges

import com.example.marketbitcoinappleovaz.data.remote.mapper.toExchange
import com.example.marketbitcoinappleovaz.utils.Resource
import com.example.marketbitcoinappleovaz.domain.model.Exchange
import com.example.marketbitcoinappleovaz.domain.repository.ExchangeRepository
import com.example.marketbitcoinappleovaz.utils.toResourceError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class GetExchangesUseCase @Inject constructor(
    private val repository: ExchangeRepository
) {
    operator fun invoke(): Flow<Resource<List<Exchange>>> = flow {
        emit(Resource.Loading())
        runCatching {
            repository.getExchanges().map { it.toExchange() }
        }.onSuccess { exchanges ->
            emit(Resource.Success(exchanges))
        }.onFailure { e ->
            emit(e.toResourceError())
        }
    }
}