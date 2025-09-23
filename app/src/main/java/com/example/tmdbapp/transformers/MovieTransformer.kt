package com.example.tmdbapp.transformers

import com.example.tmdbapp.models.Movie
import com.example.tmdbapp.network.MovieResponse

fun MovieResponse.toMovie(): Movie {
    return Movie(
        adult = this.adult,
        genreIds = this.genre_ids,
        id = this.id,
        originalLanguage = this.original_language,
        originalTitle = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        releaseDate = this.release_date,
        title = this.title,
        video = this.video,
        voteAverage = this.vote_average,
        voteCount = this.vote_count,
        posterPath = this.poster_path.let { "https://image.tmdb.org/t/p/w500$it" },
        backdropPath = this.backdrop_path?.let { "https://image.tmdb.org/t/p/w780$it" },
    )
}