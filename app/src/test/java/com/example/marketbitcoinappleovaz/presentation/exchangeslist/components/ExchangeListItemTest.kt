package com.example.marketbitcoinappleovaz.presentation.exchangeslist.components

import android.os.Build
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.assertIsDisplayed
import com.example.marketbitcoinappleovaz.Runner
import com.example.marketbitcoinappleovaz.getExchanges
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
class ExchangeListItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `WHEN ExchangeListItem is called THEN should display exchange details`() {
        val exchange = getExchanges()
        composeTestRule.setContent {
            ExchangeListItem(
                exchange = exchange,
                onItemClick = {}
            )
        }

        composeTestRule.onNodeWithTag(Constants.ComposeId.EXCHANGE_LIST_IMAGE_ID, useUnmergedTree = true).assertExists().assertIsDisplayed()
        composeTestRule.onNodeWithTag(Constants.ComposeId.EXCHANGE_LIST_TITLE_ID, useUnmergedTree = true).assertExists().assertIsDisplayed()
        composeTestRule.onNodeWithTag(Constants.ComposeId.EXCHANGE_LIST_NAME_ID, useUnmergedTree = true).assertExists().assertIsDisplayed()
        composeTestRule.onNodeWithTag(Constants.ComposeId.EXCHANGE_LIST_VOLUME_ID, useUnmergedTree = true).assertExists().assertIsDisplayed()
    }
}