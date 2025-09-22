package com.example.tmdbapp.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage

@Composable
fun RemoteIcon(
    url: String,
    contentDescription: String? = null,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = url,
        contentDescription = contentDescription,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        contentScale = ContentScale.Crop,
    )
}

@Preview(showBackground = true)
@Composable
fun RemoteIconPreview() {
    RemoteIcon(url = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/oN7FZe6qNAAb46MpgbIyua8fJy7.jpg")
}