package com.example.tmdbapp.ui

import com.example.tmdbapp.interactors.MovieInteractor
import com.example.tmdbapp.mvi.CustomViewModel

class MovieListViewModel(
    private val interactor: MovieInteractor
) : CustomViewModel<MovieListContract.State, MovieListContract.Event, MovieListContract.Effect>(
    MovieListContract.State()
) {
    override suspend fun handleEvent(event: MovieListContract.Event) {
        when (event) {
            is MovieListContract.Event.OnMovieClick -> {

            }
            MovieListContract.Event.LoadMovies -> {
                interactor.getMovies(
                    query = uiState.value.query,
                    page = 1
                ).collect { movies ->
                    setState { uiState.value.copy(
                        movies = movies
                    ) }
                }
                setState {
                    uiState.value.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
}