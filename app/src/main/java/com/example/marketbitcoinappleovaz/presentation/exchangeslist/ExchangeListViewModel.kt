package com.example.marketbitcoinappleovaz.presentation.exchangeslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketbitcoinappleovaz.domain.usecase.getexchangelistwithicon.GetExchangeListWithIconUseCase
import com.example.marketbitcoinappleovaz.utils.Constants.ApiError.UNKNOWN_ERROR
import com.example.marketbitcoinappleovaz.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ExchangeListViewModel @Inject constructor(
    private val useCase: GetExchangeListWithIconUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ExchangeListState())
    val state: StateFlow<ExchangeListState> = _state

    init {
        getExchangesWithIcons()
    }

    private fun getExchangesWithIcons() {
        useCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ExchangeListState(exchangeList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ExchangeListState(
                        error = result.message ?: UNKNOWN_ERROR
                    )
                }
                is Resource.Loading -> {
                    _state.value = ExchangeListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}