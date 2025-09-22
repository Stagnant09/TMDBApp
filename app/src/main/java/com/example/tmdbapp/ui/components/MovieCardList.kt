package com.example.tmdbapp.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.tmdbapp.models.Movie

@Composable
fun MovieCardList(
    movies: List<Movie>,
    onItemClick: (Movie) -> Unit
) {
    LazyColumn {
        items(movies.size) { index ->
            MovieCard(movies[index], onClick = { onItemClick(movies[index]) })
        }
    }
}