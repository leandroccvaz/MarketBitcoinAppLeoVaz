package com.example.marketbitcoinappleovaz.presentation.sharedcomponents

import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.marketbitcoinappleovaz.Runner
import com.example.marketbitcoinappleovaz.presentation.exchangedetail.ExchangeDetailState
import com.example.marketbitcoinappleovaz.utils.Constants
import com.example.marketbitcoinappleovaz.utils.Constants.ApiError.UNKNOWN_ERROR
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
class ExchangeStateErrorTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `WHEN ExchangeStateError is called THEN should display error message`() {
        val errorMessage = UNKNOWN_ERROR
        val state = ExchangeDetailState(error = errorMessage)

        composeTestRule.setContent {
            Box {
                ExchangeStateError(state)
            }
        }

        composeTestRule.onNodeWithTag(Constants.ComposeId.EXCHANGE_ERROR_ID).assertExists().assertIsDisplayed()
    }
}