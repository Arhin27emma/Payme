package com.techgen.payme.navigation

sealed class NavScreen(val route: String) {
    object Home : NavScreen("home")
    object Request : NavScreen("request")
    object Bank : NavScreen("bank")
    object Cards : NavScreen("cards")
} 