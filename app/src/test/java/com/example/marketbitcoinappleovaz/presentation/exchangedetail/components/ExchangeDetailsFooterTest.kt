package com.example.marketbitcoinappleovaz.presentation.exchangedetail.components

import android.os.Build
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.assertIsDisplayed
import com.example.marketbitcoinappleovaz.Runner
import com.example.marketbitcoinappleovaz.getExchangeDetails
import com.example.marketbitcoinappleovaz.utils.Constants
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
class ExchangeDetailsFooterTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `WHEN ExchangeDetailsFooter is called THEN should display rank and website`() {
        val exchangeDetails = getExchangeDetails()

        composeTestRule.setContent {
            ExchangeDetailsFooter(exchangeDetails)
        }

        composeTestRule.onNodeWithTag(Constants.ComposeId.EXCHANGE_DETAIL_RANK_ID).assertExists().assertIsDisplayed()
        composeTestRule.onNodeWithTag(Constants.ComposeId.EXCHANGE_DETAIL_WEBSITE_ID).assertExists().assertIsDisplayed()
    }
}