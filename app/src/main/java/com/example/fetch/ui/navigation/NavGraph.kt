package com.example.fetch.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.fetch.ui.list.FetchListScreen
import com.example.fetch.ui.welcome.FetchWelcomeScreen

@Composable
fun NavHost(
    navController: NavHostController,
    startDestination: String
) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(WelcomeScreen.Main.route) {
            FetchWelcomeScreen(
                onNavigateToList = {
                    navController.navigate(
                        route = ListScreen.Main.route
                    )
                },
            )
        }

        composable(ListScreen.Main.route) {
            FetchListScreen()
        }
    }
}