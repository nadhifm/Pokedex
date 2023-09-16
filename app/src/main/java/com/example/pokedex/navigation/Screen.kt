package com.example.pokedex.navigation

sealed class Screen(val route: String) {
    object Splash :  Screen("splash")
    object Home : Screen("home")
    object Detail : Screen("detail?url={url}") {
        fun createRoute(url: String) = "detail?url=$url"
    }
}