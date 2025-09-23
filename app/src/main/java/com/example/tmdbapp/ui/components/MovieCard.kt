package com.example.tmdbapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tmdbapp.models.Movie
import com.example.tmdbapp.utils.toPosterUrl

/** This composable is used to display information about a movie
 * @param movie The movie to display
 */
@Composable
fun MovieCard(movie: Movie, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(),
        elevation = CardDefaults.cardElevation(),
    ) {
        Box(
            modifier = Modifier.clip(RoundedCornerShape(6.dp))
        ) {
            Row(
                modifier = Modifier.padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RemoteIcon(
                    url = movie.posterPath?.toPosterUrl() ?: "",
                    contentDescription = movie.title,
                    modifier = Modifier
                        .height(124.dp)
                        .width(100.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column() {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = movie.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = movie.overview, fontSize = 13.sp, maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieCardPreview() {
    MovieCard(
        Movie(
            id = 1,
            title = "Roar", //
            overview = "Roar follows a family who are attacked by various African animals at the secluded home of their keeper.\n", //
            releaseDate = "1981-01-01",
            posterPath = "/oN7FZe6qNAAb46MpgbIyua8fJy7.jpg", //
            adult = false,
            backdropPath = null,
            genreIds = listOf(0),
            originalLanguage = "English",
            originalTitle = "Movie Title",
            popularity = 7.0,
            video = true,
            voteAverage = 7.0,
            voteCount = 1000,
        )
    )
}