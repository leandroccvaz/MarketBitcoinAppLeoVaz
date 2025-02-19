package com.example.marketbitcoinappleovaz.presentation.sharedcomponents

import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.assertIsDisplayed
import com.example.marketbitcoinappleovaz.Runner
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
class ExchangeStateLoadingTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `WHEN ExchangeStateLoading is called THEN should display loading indicator`() {
        composeTestRule.setContent {
            Box {
                ExchangeStateLoading()
            }
        }

        composeTestRule.onNodeWithTag(Constants.ComposeId.EXCHANGE_LOADING_ID).assertExists().assertIsDisplayed()
    }
}