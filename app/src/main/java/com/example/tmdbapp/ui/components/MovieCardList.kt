package com.example.tmdbapp.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tmdbapp.models.Movie

@Composable
fun MovieCardList(
    movies: List<Movie>,
    lazyListState: LazyListState,
    onItemClick: (Movie) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(8.dp), state = lazyListState) {
        items(movies.size) { index ->
            MovieCard(movies[index], onClick = { onItemClick(movies[index]) })
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}