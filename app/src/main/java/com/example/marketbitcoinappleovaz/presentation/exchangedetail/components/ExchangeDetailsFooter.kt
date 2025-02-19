package com.example.marketbitcoinappleovaz.presentation.exchangedetail.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.marketbitcoinappleovaz.domain.model.ExchangeDetails
import com.example.marketbitcoinappleovaz.utils.Constants

@Composable
fun ExchangeDetailsFooter(exchangeDetails: ExchangeDetails) {
    val context = LocalContext.current
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Exchange Rank: ${exchangeDetails.rank}",
        Modifier.testTag(Constants.ComposeId.EXCHANGE_DETAIL_RANK_ID)

    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Website: ${exchangeDetails.website}",
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(exchangeDetails.website))
                context.startActivity(intent)
            }
            .testTag(Constants.ComposeId.EXCHANGE_DETAIL_WEBSITE_ID)
    )
}