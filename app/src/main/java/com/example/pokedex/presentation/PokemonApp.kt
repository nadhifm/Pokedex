package com.example.pokedex.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedex.navigation.Screen
import com.example.pokedex.presentation.detail.DetailScreen
import com.example.pokedex.presentation.home.HomeScreen
import com.example.pokedex.presentation.splash.SplashScreen

@Composable
fun PokemonApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier,
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                navigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToDetail = {
                    navController.navigate(Screen.Detail.createRoute(it))
                }
            )
        }
        composable(
            Screen.Detail.route,
            arguments = listOf(navArgument("url") {
                type = NavType.StringType
                defaultValue = ""
            }),
        ) {
            DetailScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}