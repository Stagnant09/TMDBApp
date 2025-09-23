package com.example.tmdbapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.tmdbapp.ui.screens.main.navigation.MainRoute
import com.example.tmdbapp.ui.screens.main.navigation.mainScreen
import com.example.tmdbapp.ui.screens.movieList.navigation.movieListScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = MainRoute
    ) {
        mainScreen(navController = navController)
        movieListScreen(navController = navController)
    }
}