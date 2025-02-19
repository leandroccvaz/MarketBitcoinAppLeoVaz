package com.example.marketbitcoinappleovaz.presentation.exchangedetail.components

import android.os.Build
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
class VolumeFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `WHEN VolumeField is called THEN should display label and value`() {
        val label = "Volume in 24h"
        val value = "123456"
        val composeIdTitle = Constants.ComposeId.EXCHANGE_DETAIL_VOLUME_IN_DAY_TITLE_ID
        val composeIdValue = Constants.ComposeId.EXCHANGE_DETAIL_VOLUME_IN_DAY_VALUE_ID

        composeTestRule.setContent {
            VolumeField(
                label = label,
                value = value,
                composeIdTitle = composeIdTitle,
                composeIdValue = composeIdValue
            )
        }

        composeTestRule.onNodeWithTag(composeIdTitle).assertExists().assertIsDisplayed()
        composeTestRule.onNodeWithTag(composeIdValue).assertExists().assertIsDisplayed()
    }
}