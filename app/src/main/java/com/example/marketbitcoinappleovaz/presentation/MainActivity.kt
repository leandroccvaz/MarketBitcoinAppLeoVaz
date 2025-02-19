package com.example.marketbitcoinappleovaz.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marketbitcoinappleovaz.presentation.exchangedetail.ExchangeDetailScreen
import com.example.marketbitcoinappleovaz.presentation.exchangeslist.ExchangeListScreen
import com.example.marketbitcoinappleovaz.presentation.ui.theme.MarketBitcoinAppLeoVazTheme
import com.example.marketbitcoinappleovaz.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarketBitcoinAppLeoVazTheme(darkTheme = true) {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background)
                )
                { innerPadding ->
                    MarketBitcoinNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    private fun MarketBitcoinNavigation(modifier: Modifier) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Constants.Route.COIN_LIST_SCREEN,
        ) {
            composable(Constants.Route.COIN_LIST_SCREEN) {
                ExchangeListScreen(
                    navController = navController,
                    modifier = modifier
                )
            }
            composable(
                route = "${Constants.Route.COIN_LIST_DETAIL_SCREEN}/{${Constants.PARAM_EXCHANGE_ID}}",
            )
            {
                val exchangeIcon =
                    navController.previousBackStackEntry?.savedStateHandle?.get<String>(Constants.PARAM_EXCHANGE_ICON)
                ExchangeDetailScreen(
                    navController = navController,
                    exchangeIcon = exchangeIcon.orEmpty()
                )
            }
        }
    }

}