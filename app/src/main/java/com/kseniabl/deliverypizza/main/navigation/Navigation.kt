package com.kseniabl.deliverypizza.main.navigation

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kseniabl.main.menu.presentation.MenuScreen
import com.kseniabl.main.menu.presentation.MenuViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    listState: LazyListState
) {
    NavHost(navController = navController, startDestination = Routes.Menu.route) {
        composable(Routes.Menu.route) {
            val viewModel = hiltViewModel<MenuViewModel>()
            MenuScreen(listState, viewModel)
        }
        composable(Routes.Profile.route) {
            // future integration
        }
        composable(Routes.Basket.route) {
            // future integration
        }
    }

}