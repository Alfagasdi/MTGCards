package com.example.mtgcards.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mtgcards.components.advancedSeach.AdvancedSearchScreen
import com.example.mtgcards.components.home.HomeScreen
import com.example.mtgcards.components.result.ResultScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(route = Routes.Home.route) {
            HomeScreen(
                advancedSearch = {
                    navController.navigate(Routes.AdvancedSearch.route)
                },
                normalSearch = {
                    navController.navigate(Routes.NormalSearch.create(it))
                }
            )
        }
        composable(route = Routes.AdvancedSearch.route) {
            AdvancedSearchScreen()
        }
        composable(route = Routes.NormalSearch.route, arguments = Routes.NormalSearch.arg
        ) {
            ResultScreen()
        }
    }
}

sealed class Routes(
    val route: String,
    val arg: List<NamedNavArgument> = emptyList()
) {
    data object Home : Routes(
        route = "home"
    )

    data object AdvancedSearch : Routes(
        route = "advanced"
    )

    data object NormalSearch : Routes(
        route = "normal/?card={card}",
        arg = listOf(
            navArgument("card") {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    ) {
        fun create(card: String) = "normal/${card}"
    }
}