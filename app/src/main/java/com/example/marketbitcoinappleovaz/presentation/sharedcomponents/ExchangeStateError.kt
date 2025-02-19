package com.example.marketbitcoinappleovaz.presentation.sharedcomponents

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.marketbitcoinappleovaz.presentation.exchangedetail.ExchangeDetailState
import com.example.marketbitcoinappleovaz.presentation.exchangeslist.ExchangeListState
import com.example.marketbitcoinappleovaz.utils.Constants

@Composable
fun <T> BoxScope.ExchangeStateError(state: T) {
    val error = when (state) {
        is ExchangeDetailState -> state.error
        is ExchangeListState -> state.error
        else -> "Unknown error"
    }
    Text(
        text = error,
        color = MaterialTheme.colorScheme.error,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .align(Alignment.Center)
            .testTag(Constants.ComposeId.EXCHANGE_ERROR_ID)
    )
}