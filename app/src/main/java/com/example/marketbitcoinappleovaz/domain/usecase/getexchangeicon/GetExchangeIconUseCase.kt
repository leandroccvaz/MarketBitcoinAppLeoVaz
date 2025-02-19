package com.example.marketbitcoinappleovaz.domain.usecase.getexchangeicon

import com.example.marketbitcoinappleovaz.data.remote.mapper.toExchangeIcon
import com.example.marketbitcoinappleovaz.domain.model.ExchangeIcon
import com.example.marketbitcoinappleovaz.domain.repository.ExchangeRepository
import com.example.marketbitcoinappleovaz.utils.Resource
import com.example.marketbitcoinappleovaz.utils.toResourceError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetExchangeIconUseCase @Inject constructor(
    private val repository: ExchangeRepository
) {
    operator fun invoke(iconSize: Int): Flow<Resource<List<ExchangeIcon>>> = flow {
        emit(Resource.Loading())
        runCatching {
            repository.getExchangeIcon(iconSize).map { it.toExchangeIcon() }
        }.onSuccess { exchangeIcon ->
            emit(Resource.Success(exchangeIcon))
        }.onFailure { e ->
            emit(e.toResourceError())
        }
    }
}