package com.example.tmdbapp.statics

import com.example.tmdbapp.models.Movie

object MovieDetails {
    var movie = Movie(
        id = 1,
        title = "Roar: A Gripping Tale of the Wild Savanna and Human Survival",
        overview = "Roar follows a family who are attacked by various African animals including lions, tigers, and elephants at the secluded home of their eccentric animal-loving patriarch. What was meant to be a reunion turns into a chaotic and terrifying fight for survival against the untamed forces of nature. This film is infamous for the real injuries sustained by cast and crew during its production.", // Longer overview
        releaseDate = "1981-11-20",
        posterPath = "/oN7FZe6qNAAb46MpgbIyua8fJy7.jpg",
        adult = false,
        backdropPath = "/oN7FZe6qNAAb46MpgbIyua8fJy7.jpg",
        genreIds = listOf(12, 18, 53),
        originalLanguage = "en",
        originalTitle = "Roar",
        popularity = 17.771,
        video = false,
        voteAverage = 6.2,
        voteCount = 188,
    )
}