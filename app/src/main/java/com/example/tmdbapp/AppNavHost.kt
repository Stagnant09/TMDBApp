package com.example.tmdbapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.tmdbapp.ui.screens.movieList.navigation.MovieListRoute
import com.example.tmdbapp.ui.screens.movieList.navigation.movieListScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = MovieListRoute
    ) {
        movieListScreen(navController = navController)
    }
}