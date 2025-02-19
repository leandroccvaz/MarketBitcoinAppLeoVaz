package com.example.marketbitcoinappleovaz.presentation.exchangedetail

import android.os.Build
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.NavController
import androidx.test.espresso.Espresso
import com.example.marketbitcoinappleovaz.Runner
import com.example.marketbitcoinappleovaz.getExchangeDetails
import com.example.marketbitcoinappleovaz.utils.Constants.ComposeId.EXCHANGE_DETAIL_IMAGE_ID
import com.example.marketbitcoinappleovaz.utils.Constants.ComposeId.EXCHANGE_DETAIL_TITLE_ID
import com.example.marketbitcoinappleovaz.utils.Constants.ComposeId.EXCHANGE_DETAIL_VOLUME_IN_DAY_TITLE_ID
import com.example.marketbitcoinappleovaz.utils.Constants.ComposeId.EXCHANGE_DETAIL_VOLUME_IN_DAY_VALUE_ID
import com.example.marketbitcoinappleovaz.utils.Constants.ComposeId.EXCHANGE_DETAIL_VOLUME_IN_HOUR_TITLE_ID
import com.example.marketbitcoinappleovaz.utils.Constants.ComposeId.EXCHANGE_DETAIL_VOLUME_IN_HOUR_VALUE_ID
import com.example.marketbitcoinappleovaz.utils.Constants.ComposeId.EXCHANGE_DETAIL_VOLUME_IN_MONTH_TITLE_ID
import com.example.marketbitcoinappleovaz.utils.Constants.ComposeId.EXCHANGE_DETAIL_VOLUME_IN_MONTH_VALUE_ID
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(Runner::class)
@Config(
    sdk = [Build.VERSION_CODES.O_MR1], instrumentedPackages = [
        "androidx.loader.content"
    ]
)
class ExchangeDetailScreenTest {

    private val viewModel = mockk<ExchangeDetailViewModel>(relaxed = true)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `WHEN ExchangeDetailScreen is loaded THEN should display exchange details`() {
        val value = getExchangeDetails()
        val navController = mockk<NavController>(relaxed = true)
        every { viewModel.state } returns MutableStateFlow(
            ExchangeDetailState(exchangeDetails = value)
        )
        composeTestRule.setContent {
            ExchangeDetailScreen(
                navController = navController,
                exchangeIcon = "https://fakeurl.com/icon.png",
                viewModel = viewModel
            )
        }

        composeTestRule.onNodeWithTag(EXCHANGE_DETAIL_IMAGE_ID, useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag(EXCHANGE_DETAIL_TITLE_ID, useUnmergedTree = true).assertExists().assertTextEquals(value.name)
        composeTestRule.onNodeWithTag(EXCHANGE_DETAIL_VOLUME_IN_HOUR_TITLE_ID, useUnmergedTree = true).assertExists().assertTextEquals("Volume em horas:")
        composeTestRule.onNodeWithTag(EXCHANGE_DETAIL_VOLUME_IN_HOUR_VALUE_ID, useUnmergedTree = true).assertExists().assertTextEquals(value.volume1hrsUsd.toString())
        composeTestRule.onNodeWithTag(EXCHANGE_DETAIL_VOLUME_IN_DAY_TITLE_ID, useUnmergedTree = true).assertExists().assertTextEquals("Volume em dias:")
        composeTestRule.onNodeWithTag(EXCHANGE_DETAIL_VOLUME_IN_DAY_VALUE_ID, useUnmergedTree = true).assertExists().assertTextEquals(value.volume1dayUsd.toString())
        composeTestRule.onNodeWithTag(EXCHANGE_DETAIL_VOLUME_IN_MONTH_TITLE_ID, useUnmergedTree = true).assertExists().assertTextEquals("Volume em meses:")
        composeTestRule.onNodeWithTag(EXCHANGE_DETAIL_VOLUME_IN_MONTH_VALUE_ID, useUnmergedTree = true).assertExists().assertTextEquals(value.volume1mthUsd.toString())
    }

    @Test
    fun `WHEN back button is pressed THEN should navigate back`() {
        every { viewModel.state } returns MutableStateFlow(ExchangeDetailState())
        val navController = mockk<NavController>(relaxed = true)
        composeTestRule.setContent {
            ExchangeDetailScreen(
                navController = navController,
                exchangeIcon = "https://fakeurl.com/icon.png",
                viewModel = viewModel
            )
        }

        Espresso.pressBack()

        verify { navController.popBackStack() }
    }

}