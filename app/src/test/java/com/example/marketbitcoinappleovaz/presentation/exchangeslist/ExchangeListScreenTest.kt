package com.example.marketbitcoinappleovaz.presentation.exchangeslist

import android.os.Build
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.marketbitcoinappleovaz.Runner
import com.example.marketbitcoinappleovaz.getExchanges
import com.example.marketbitcoinappleovaz.utils.Constants
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import io.mockk.verify

@RunWith(Runner::class)
@Config(
    sdk = [Build.VERSION_CODES.O_MR1], instrumentedPackages = [
        "androidx.loader.content"
    ]
)
class ExchangeListScreenTest {

    private val viewModel: ExchangeListViewModel = mockk(relaxed = true)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `WHEN ExchangeListScreen is called THEN should display search field and exchange list`() {
        val exchanges = listOf(
            getExchanges().copy(exchangeId = "exchange1", name = "Bitcoin"),
            getExchanges().copy(exchangeId = "exchange2", name = "Ethereum")
        )
        val state = ExchangeListState(exchangeList = exchanges)
        every { viewModel.state } returns MutableStateFlow(state)

        composeTestRule.setContent {
            ExchangeListScreen(
                navController = rememberNavController(),
                viewModel = viewModel
            )
        }

        composeTestRule.onNodeWithText("Search").assertExists().assertIsDisplayed()
        exchanges.forEach { exchange ->
            composeTestRule.onNodeWithText(exchange.name, useUnmergedTree = true).assertExists().assertIsDisplayed()
            composeTestRule.onNodeWithText("ID: ${exchange.exchangeId}", useUnmergedTree = true).assertExists()
                .assertIsDisplayed()
        }
    }

    @Test
    fun `WHEN Exchange item is clicked THEN should navigate to detail screen`() {
        val exchanges = listOf(
            getExchanges().copy(exchangeId = "exchange1", name = "Bitcoin"),
            getExchanges().copy(exchangeId = "exchange2", name = "Ethereum")
        )
        val state = ExchangeListState(exchangeList = exchanges)
        val navController = mockk<NavController>(relaxed = true)
        justRun { navController.navigate(route = any()) }
        every { viewModel.state } returns MutableStateFlow(state)
        composeTestRule.setContent {
            ExchangeListScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composeTestRule.onNodeWithText("Bitcoin").performClick()

        val expectedRoute = "${Constants.Route.COIN_LIST_DETAIL_SCREEN}/exchange1"
        verify { navController.navigate(route = expectedRoute) }
    }
}