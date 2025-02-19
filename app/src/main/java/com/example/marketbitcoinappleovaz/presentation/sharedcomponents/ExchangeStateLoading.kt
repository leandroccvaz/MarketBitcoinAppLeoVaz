package com.example.marketbitcoinappleovaz.presentation.sharedcomponents

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.marketbitcoinappleovaz.utils.Constants

@Composable
fun BoxScope.ExchangeStateLoading() {
    CircularProgressIndicator(
        modifier = Modifier
            .size(70.dp)
            .align(Alignment.Center)
            .testTag(Constants.ComposeId.EXCHANGE_LOADING_ID),
        strokeWidth = 8.dp
    )
}