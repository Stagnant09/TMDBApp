package com.example.tmdbapp.ui.screens.movieDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdbapp.statics.MovieDetails
import com.example.tmdbapp.ui.components.ExpandableText
import com.example.tmdbapp.ui.components.RemoteIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    onBack: () -> Unit,
) {
    val movie = MovieDetails.movie
    val scrollState = rememberScrollState()
    val placeholderPoster = "https://via.placeholder.com/500x750.png?text=No+Poster"
    val placeholderBackdrop = "https://via.placeholder.com/1280x720.png?text=No+Backdrop"

    val baseImageUrl = "https://image.tmdb.org/t/p/"
    val backdropSize = "w1280" // Common backdrop size
    val posterSize = "w500"   // Common poster size

    val fullBackdropPath = movie.backdropPath?.let { "$baseImageUrl$backdropSize$it" } ?: placeholderBackdrop
    val fullPosterPath = movie.posterPath?.let { "$baseImageUrl$posterSize$it" } ?: placeholderPoster

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = movie.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // --- Backdrop Image ---
            RemoteIcon(
                url = fullBackdropPath,
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .graphicsLayer(alpha = 0.6f)
            )

            // --- Main Info Section (Poster, Title, Release, Rating) ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.Top // Align poster and text block at the top
            ) {
                RemoteIcon(
                    url = fullPosterPath,
                    contentDescription = movie.title,
                    modifier = Modifier
                        .height(160.dp)
                        .width(100.dp)
                        .clip(RoundedCornerShape(6.dp))
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Title, Release Date, Rating
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Release Date: ${movie.releaseDate?.ifEmpty { "N/A" }}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFFFC107), // Gold color for star
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${String.format("%.1f", movie.voteAverage)}/10 (${movie.voteCount} votes)",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Text(
                        text = "Popularity: ${String.format("%.2f", movie.popularity)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    if (movie.adult) {
                        Text(
                            text = "Adult: Yes",
                            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.error)
                        )
                    }
                }
            }

            // --- Overview Section ---
            Text(
                text = "Overview",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                ExpandableText(
                    text = movie.overview.ifEmpty { "No overview available." },
                    minimizedMaxLines = 4
                )
            }

            // --- Additional Details Section ---
            HorizontalDivider(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 2.dp,
                    bottom = 8.dp
                )
            )
            Text(
                text = "More Details",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 8.dp
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
            Column(modifier = Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                DetailItem("Original Title:", movie.originalTitle ?: "N/A")
                DetailItem("Original Language:", movie.originalLanguage?.uppercase() ?: "N/A")
                DetailItem("Video Available:", if (movie.video) "Yes" else "No")
                DetailItem("Genre IDs:", movie.genreIds?.joinToString()?.ifEmpty { "N/A" } ?: "N/A")
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    if (value.isNotEmpty()) {
        Row {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.width(140.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


@Preview(showBackground = true, widthDp = 400, heightDp = 900)
@Composable
fun MovieDetailsScreenPreview() {
    MaterialTheme {
        MovieDetailsScreen(
            onBack = { }
        )
    }
}