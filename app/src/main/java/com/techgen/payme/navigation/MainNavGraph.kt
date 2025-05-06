package com.techgen.payme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.techgen.payme.ui.wallet.WalletScreen
import com.techgen.payme.ui.bank.BankScreen
import com.techgen.payme.ui.bank.BankDetailScreen
import com.techgen.payme.ui.cards.CardsScreen
import com.techgen.payme.ui.cards.CardDetailScreen
import com.techgen.payme.viewmodel.BankAccountItem
import com.techgen.payme.viewmodel.CardItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun MainNavGraph(navController: NavHostController) {
    val darkBackground = Color(0xFF181A20)
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Bank", "Cards")

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = darkBackground,
                contentColor = Color.White
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = when (index) {
                                    0 -> Icons.Default.Home
                                    1 -> Icons.Default.AccountBox
                                    2 -> Icons.Default.AccountCircle
                                    else -> Icons.Default.Home
                                },
                                contentDescription = item
                            )
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            when (index) {
                                0 -> navController.navigate("home")
                                1 -> navController.navigate("bank")
                                2 -> navController.navigate("cards")
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                WalletScreen(navController)
            }
            composable("bank") {
                BankScreen(
                    onAccountClick = { account ->
                        navController.navigate("bank_detail/${account.id}")
                    }
                )
            }
            composable("bank_detail/{accountId}") { backStackEntry ->
                val accountId = backStackEntry.arguments?.getString("accountId")
                val account = BankAccountItem(accountId ?: "", "Account $accountId", 0.0)
                BankDetailScreen(
                    account = account,
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable("cards") {
                CardsScreen(
                    onCardClick = { card ->
                        navController.navigate("card_detail/${card.id}")
                    }
                )
            }
            composable("card_detail/{cardId}") { backStackEntry ->
                val cardId = backStackEntry.arguments?.getString("cardId")
                val card = CardItem(cardId ?: "", "Card $cardId", "Expiry Date")
                CardDetailScreen(
                    card = card,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
} 