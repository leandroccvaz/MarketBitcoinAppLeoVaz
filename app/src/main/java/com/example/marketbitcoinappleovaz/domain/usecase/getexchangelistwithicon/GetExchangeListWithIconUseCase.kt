package com.example.marketbitcoinappleovaz.domain.usecase.getexchangelistwithicon

import com.example.marketbitcoinappleovaz.domain.model.Exchange
import com.example.marketbitcoinappleovaz.domain.usecase.getexchangeicon.GetExchangeIconUseCase
import com.example.marketbitcoinappleovaz.domain.usecase.getexchanges.GetExchangesUseCase
import com.example.marketbitcoinappleovaz.utils.Constants
import com.example.marketbitcoinappleovaz.utils.Constants.ApiError.UNKNOWN_ERROR
import com.example.marketbitcoinappleovaz.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetExchangeListWithIconUseCase @Inject constructor(
    private val getExchangeIconUseCase: GetExchangeIconUseCase,
    private val getExchangesUseCase: GetExchangesUseCase,
) {
    operator fun invoke(): Flow<Resource<List<Exchange>>> = flow {
        emit(Resource.Loading())

        val exchangesFlow = getExchangesUseCase().flowOn(Dispatchers.IO)
        val iconsFlow = getExchangeIconUseCase(Constants.PARAM_ICON_SIZE).flowOn(Dispatchers.IO)

        combine(exchangesFlow, iconsFlow) { exchangesResource, iconsResource ->
            when {
                exchangesResource is Resource.Success && iconsResource is Resource.Success -> {
                    val iconMap = iconsResource.data?.associateBy { it.exchangeId }
                    val exchangesWithIcons = exchangesResource.data?.map { exchange ->
                        exchange.copy(iconUrl = iconMap?.get(exchange.exchangeId)?.url)
                    } ?: emptyList()
                    Resource.Success(exchangesWithIcons)
                }
                exchangesResource is Resource.Error -> {
                    Resource.Error(exchangesResource.message ?: UNKNOWN_ERROR)
                }
                iconsResource is Resource.Error -> {
                    Resource.Error(iconsResource.message ?: UNKNOWN_ERROR)
                }
                else -> {
                    Resource.Loading()
                }
            }
        }.collect { result ->
            emit(result)
        }
    }.catch { e ->
        emit(Resource.Error(e.message ?: UNKNOWN_ERROR))
    }
}