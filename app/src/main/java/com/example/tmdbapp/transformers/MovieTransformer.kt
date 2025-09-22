package com.example.tmdbapp.transformers

import com.example.tmdbapp.models.Movie
import com.example.tmdbapp.network.MovieResponse

fun MovieResponse.toMovie(): Movie {
    return Movie(
        adult = this.adult,
        genreIds = this.genreIds,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        posterPath = posterPath.let { "https://image.tmdb.org/t/p/w500$it" },
        backdropPath = backdropPath?.let { "https://image.tmdb.org/t/p/w780$it" },
    )
}

// Optional: Transformer for a list of MovieResponses
fun List<MovieResponse>.toMovies(): List<Movie> {
    return this.map { it.toMovie() }
}