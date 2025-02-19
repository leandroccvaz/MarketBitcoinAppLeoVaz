package com.example.marketbitcoinappleovaz.presentation.exchangeslist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.marketbitcoinappleovaz.presentation.exchangeslist.components.ExchangeListItem
import com.example.marketbitcoinappleovaz.presentation.sharedcomponents.ExchangeStateError
import com.example.marketbitcoinappleovaz.presentation.sharedcomponents.ExchangeStateLoading
import com.example.marketbitcoinappleovaz.utils.Constants

@Composable
fun ExchangeListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ExchangeListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState().value
    var searchText by remember { mutableStateOf("") }

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = modifier.padding(16.dp)) {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = modifier.fillMaxWidth(),
                placeholder = { Text("Search") }
            )

            LazyColumn(modifier = modifier.fillMaxSize()) {
                val filteredList = state.exchangeList.filter {
                    it.name.contains(searchText, ignoreCase = true) ||
                            it.exchangeId.contains(searchText, ignoreCase = true)
                }
                items(filteredList.size) { index ->
                    val exchange = filteredList[index]
                    ExchangeListItem(
                        exchange = exchange,
                        onItemClick = {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                Constants.PARAM_EXCHANGE_ICON, exchange.iconUrl
                            )
                            navController.navigate(
                                route = "${Constants.Route.COIN_LIST_DETAIL_SCREEN}/${exchange.exchangeId}"
                            )
                        }
                    )
                }
            }
        }
        if (state.error.isNotBlank()) {
            ExchangeStateError(state = state)
        }
        if (state.isLoading) {
            ExchangeStateLoading()
        }
    }
}