package com.example.tmdbapp.interactors

import com.example.tmdbapp.models.Movie
import com.example.tmdbapp.network.MovieResponse
import com.example.tmdbapp.repository.MovieRepository
import com.example.tmdbapp.transformers.toMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

open class MovieInteractor(
    private val repository: MovieRepository = MovieRepository
) {
    open fun getMovies(
        query: String,
        page: Int
    ): Flow<List<Movie>> = flow {
        val movieResponses: List<MovieResponse> = repository.fetchMovies(
            query = query,
            page = page
        )
        val movies: List<Movie> = movieResponses.map { it.toMovie() }

        emit(movies)
    }
        .flowOn(Dispatchers.IO)
        .catch {
            System.err.println("Error fetching movies: ${it.message}")
            emit(emptyList<Movie>()) // Emit an empty list in case of an error
        }
}