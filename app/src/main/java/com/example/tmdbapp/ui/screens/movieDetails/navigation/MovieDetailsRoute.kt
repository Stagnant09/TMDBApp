package com.example.tmdbapp.ui.screens.movieDetails.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.tmdbapp.models.Movie
import com.example.tmdbapp.ui.screens.movieDetails.MovieDetailsScreen
import kotlinx.serialization.Serializable

@Serializable
data object MovieDetailsRoute

fun NavController.navigateToMovieDetails(
    navOptions: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(MovieDetailsRoute, navOptions)
}

fun NavGraphBuilder.movieDetailsScreen(
    navController: NavHostController,
) {
    composable<MovieDetailsRoute> { backStackEntry ->
        val route: MovieDetailsRoute = backStackEntry.toRoute()
        MovieDetailsScreen(
            onBack = { navController.navigateUp() }
        )
    }
}
