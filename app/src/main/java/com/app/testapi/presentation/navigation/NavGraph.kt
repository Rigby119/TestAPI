package com.app.testapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.testapi.presentation.screens.game.GameScreen
import com.app.testapi.presentation.screens.home.HomeScreen

@Suppress("ktlint:standard:function-naming")
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable("home") {
            HomeScreen(
                onGameSelected = { size ->
                    navController.navigate("game/$size")
                },
            )
        }

        composable(
            route = "game/{size}",
            arguments =
                listOf(
                    navArgument("size") {
                        type = NavType.IntType
                        defaultValue = 9
                    },
                ),
        ) {
            GameScreen(
                onBackToHome = { navController.popBackStack() },
            )
        }
        composable(
            route = "game/{size}",
            arguments =
                listOf(
                    navArgument("size") {
                        type = NavType.IntType
                        defaultValue = 9
                    },
                ),
        ) {
            GameScreen(
                onBackToHome = { navController.popBackStack() },
            )
        }
    }
}
