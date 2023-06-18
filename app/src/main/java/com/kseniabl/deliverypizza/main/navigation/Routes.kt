package com.kseniabl.deliverypizza.main.navigation

sealed class Routes(val route: String) {
    object Menu : Routes("menu")
    object Profile : Routes("profile")
    object Basket : Routes("basket")
}