package com.example.marketbitcoinappleovaz.presentation.exchangedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketbitcoinappleovaz.domain.usecase.getexchange.GetExchangeUseCase
import com.example.marketbitcoinappleovaz.utils.Constants
import com.example.marketbitcoinappleovaz.utils.Constants.ApiError.UNKNOWN_ERROR
import com.example.marketbitcoinappleovaz.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ExchangeDetailViewModel @Inject constructor(
    private val useCase: GetExchangeUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(ExchangeDetailState())
    val state: StateFlow<ExchangeDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_EXCHANGE_ID)?.let { exchangeId ->
            getExchange(exchangeId)
        }
    }

    private fun getExchange(exchangeId: String) {
        useCase(exchangeId)
            .flowOn(Dispatchers.IO)
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = ExchangeDetailState(exchangeDetails = result.data)
                    }

                    is Resource.Error -> {
                        _state.value = ExchangeDetailState(
                            error = result.message ?: UNKNOWN_ERROR
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = ExchangeDetailState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }
}