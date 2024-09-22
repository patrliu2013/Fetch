package com.example.fetch.ui.navigation

sealed class WelcomeScreen(val route: String) {
    object Main: WelcomeScreen("welcome-screen")
    object ExampleWithPathArgument: WelcomeScreen("welcome-screen/{key}") {
        //Key Name
        const val KEY = "key"

        //sub route
        fun routeWithKey(key: String): String =
            route.replace("{$KEY}", key)
    }
}

sealed class ListScreen(val route: String) {
    object Main: ListScreen("list-screen")
}