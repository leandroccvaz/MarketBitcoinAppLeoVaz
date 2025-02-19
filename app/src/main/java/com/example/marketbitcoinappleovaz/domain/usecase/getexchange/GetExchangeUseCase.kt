package com.example.marketbitcoinappleovaz.domain.usecase.getexchange

import com.example.marketbitcoinappleovaz.data.remote.mapper.toExchangeDetails
import com.example.marketbitcoinappleovaz.utils.Resource
import com.example.marketbitcoinappleovaz.domain.model.ExchangeDetails
import com.example.marketbitcoinappleovaz.domain.repository.ExchangeRepository
import com.example.marketbitcoinappleovaz.utils.toResourceError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetExchangeUseCase @Inject constructor(
    private val repository: ExchangeRepository
) {

    operator fun invoke(exchangeId: String): Flow<Resource<ExchangeDetails>> = flow {
        emit(Resource.Loading())
        runCatching {
            repository.getExchangeById(exchangeId).first().toExchangeDetails()
        }.onSuccess { exchangeDetails ->
            emit(Resource.Success(exchangeDetails))
        }.onFailure { e ->
            emit(e.toResourceError())
        }
    }
}