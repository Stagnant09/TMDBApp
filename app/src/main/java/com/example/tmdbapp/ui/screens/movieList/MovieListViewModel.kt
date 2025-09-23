package com.example.tmdbapp.ui.screens.movieList

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.interactors.MovieInteractor
import com.example.tmdbapp.mvi.CustomViewModel
import com.example.tmdbapp.statics.MovieDetails
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val interactor: MovieInteractor,
    private val query: String
) : CustomViewModel<MovieListContract.State, MovieListContract.Event, MovieListContract.Effect>(
    MovieListContract.State(
        isLoading = true,
        query = query
    )
) {
    init {
        loadMovies() // automatically load on init
    }

    private fun loadMovies() {
        viewModelScope.launch {
            interactor.getMovies(query, page = 1)
                .collect { movies ->
                    setState { current ->
                        current.copy(
                            movies = movies,
                            isLoading = false
                        )
                    }
                }
        }
    }

    override suspend fun handleEvent(event: MovieListContract.Event) {
        when (event) {
            is MovieListContract.Event.OnMovieClick -> {
                Log.d("MovieListViewModel", "Navigating to movie details")
                MovieDetails.movie = event.movie
                setEffect {
                    MovieListContract.Effect.NavigateToMovieDetails
                }
            }
            MovieListContract.Event.LoadMovies -> {
                loadMovies()
            }
            MovieListContract.Event.Refresh -> {
                setState {
                    uiState.value.copy(
                        isLoading = true
                    )
                }
                loadMovies()
            }
        }
    }
}