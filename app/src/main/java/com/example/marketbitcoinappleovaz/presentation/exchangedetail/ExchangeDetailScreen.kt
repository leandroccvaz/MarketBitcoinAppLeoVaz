package com.example.marketbitcoinappleovaz.presentation.exchangedetail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.marketbitcoinappleovaz.presentation.exchangedetail.components.ExchangeDetailsFooter
import com.example.marketbitcoinappleovaz.presentation.exchangedetail.components.VolumeField
import com.example.marketbitcoinappleovaz.presentation.sharedcomponents.ExchangeStateError
import com.example.marketbitcoinappleovaz.presentation.sharedcomponents.ExchangeStateLoading
import com.example.marketbitcoinappleovaz.utils.Constants.ComposeId.EXCHANGE_DETAIL_IMAGE_ID
import com.example.marketbitcoinappleovaz.utils.Constants.ComposeId.EXCHANGE_DETAIL_TITLE_ID
import com.example.marketbitcoinappleovaz.utils.Constants.ComposeId.EXCHANGE_DETAIL_VOLUME_IN_DAY_TITLE_ID
import com.example.marketbitcoinappleovaz.utils.Constants.ComposeId.EXCHANGE_DETAIL_VOLUME_IN_DAY_VALUE_ID
import com.example.marketbitcoinappleovaz.utils.Constants.ComposeId.EXCHANGE_DETAIL_VOLUME_IN_HOUR_TITLE_ID
import com.example.marketbitcoinappleovaz.utils.Constants.ComposeId.EXCHANGE_DETAIL_VOLUME_IN_HOUR_VALUE_ID
import com.example.marketbitcoinappleovaz.utils.Constants.ComposeId.EXCHANGE_DETAIL_VOLUME_IN_MONTH_TITLE_ID
import com.example.marketbitcoinappleovaz.utils.Constants.ComposeId.EXCHANGE_DETAIL_VOLUME_IN_MONTH_VALUE_ID

@Composable
fun ExchangeDetailScreen(
    navController: NavController,
    exchangeIcon: String,
    viewModel: ExchangeDetailViewModel = hiltViewModel()
) {
    BackHandler { navController.popBackStack() }
    val state = viewModel.state.collectAsState().value
    Box(modifier = Modifier.fillMaxSize()) {
        state.exchangeDetails?.let { exchangeDetails ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = exchangeIcon),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .testTag(EXCHANGE_DETAIL_IMAGE_ID)
                        .size(70.dp)
                        .padding(vertical = 16.dp)
                )

                Text(
                    text = exchangeDetails.name,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .testTag(EXCHANGE_DETAIL_TITLE_ID)
                        .padding(bottom = 16.dp)
                )

                VolumeField(
                    label = "Volume em horas:",
                    value = exchangeDetails.volume1hrsUsd.toString(),
                    composeIdTitle = EXCHANGE_DETAIL_VOLUME_IN_HOUR_TITLE_ID,
                    composeIdValue = EXCHANGE_DETAIL_VOLUME_IN_HOUR_VALUE_ID
                )
                VolumeField(
                    label = "Volume em dias:",
                    value = exchangeDetails.volume1dayUsd.toString(),
                    composeIdTitle = EXCHANGE_DETAIL_VOLUME_IN_DAY_TITLE_ID,
                    composeIdValue = EXCHANGE_DETAIL_VOLUME_IN_DAY_VALUE_ID
                )
                VolumeField(
                    label = "Volume em meses:",
                    value = exchangeDetails.volume1mthUsd.toString(),
                    composeIdTitle = EXCHANGE_DETAIL_VOLUME_IN_MONTH_TITLE_ID,
                    composeIdValue = EXCHANGE_DETAIL_VOLUME_IN_MONTH_VALUE_ID
                )

                ExchangeDetailsFooter(exchangeDetails = exchangeDetails)
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