package com.example.tmdbapp.ui.screens.movieList

import com.example.tmdbapp.models.Movie
import com.example.tmdbapp.mvi.CustomEffect
import com.example.tmdbapp.mvi.CustomEvent
import com.example.tmdbapp.mvi.CustomState

sealed interface MovieListContract {

    sealed interface Event : CustomEvent {
        data object Init : Event
        data object LoadMovies : Event
        data class OnMovieClick(val movie: Movie) : Event
    }

    data class State(
        val query: String = "alien",
        val isLoading: Boolean = false,
        val movies: List<Movie> = emptyList()
    ) : CustomState

    sealed interface Effect : CustomEffect {}

}