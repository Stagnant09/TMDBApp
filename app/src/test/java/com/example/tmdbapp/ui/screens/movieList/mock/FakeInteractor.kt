package com.example.tmdbapp.ui.screens.movieList.mock

import com.example.tmdbapp.interactors.MovieInteractor
import com.example.tmdbapp.models.Movie
import kotlinx.coroutines.flow.flowOf

class FakeMovieInteractor : MovieInteractor() {
    var moviesToReturn: List<Movie> = emptyList()

    override fun getMovies(query: String, page: Int) = flowOf(moviesToReturn)
}