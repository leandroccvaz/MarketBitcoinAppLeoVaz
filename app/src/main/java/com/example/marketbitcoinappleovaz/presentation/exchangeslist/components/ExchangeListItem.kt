package com.example.marketbitcoinappleovaz.presentation.exchangeslist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.marketbitcoinappleovaz.R
import com.example.marketbitcoinappleovaz.domain.model.Exchange
import com.example.marketbitcoinappleovaz.utils.Constants

@Composable
fun ExchangeListItem(
    exchange: Exchange,
    onItemClick: (Exchange) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(exchange) }
            .testTag(Constants.ComposeId.EXCHANGE_LIST_CLICK_ID),
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = exchange.iconUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(Constants.PARAM_ICON_SIZE.dp)
                        .padding(end = 4.dp)
                        .testTag(Constants.ComposeId.EXCHANGE_LIST_IMAGE_ID),
                    contentScale = ContentScale.Fit
                )
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.testTag(Constants.ComposeId.EXCHANGE_LIST_TITLE_ID),
                        text = exchange.name,
                        style = MaterialTheme.typography.titleLarge,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Text(
                        modifier = Modifier.testTag(Constants.ComposeId.EXCHANGE_LIST_NAME_ID),
                        text = "ID: ${exchange.exchangeId}",
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Text(
                        modifier = Modifier.testTag(Constants.ComposeId.EXCHANGE_LIST_VOLUME_ID),
                        text = "Volume: ${exchange.volume1dayUsd}",
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.baseline_more_vert_24),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}