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

class MovieInteractor(
    private val repository: MovieRepository = MovieRepository
) {
    fun getMovies(
        query: String,
        page: Int
    ): Flow<List<Movie>> = flow {
        // 1. Fetch the list of MovieResponse objects
        val movieResponses: List<MovieResponse> = repository.fetchMovies(
            query = query,
            page = page
        )
        // 2. Transform the list of MovieResponse to a list of Movie
        val movies: List<Movie> = movieResponses.map { it.toMovie() } // Using the extension function

        // 3. Emit the transformed list of Movie objects
        emit(movies)
    }
        .flowOn(Dispatchers.IO) // Perform repository call and transformation on IO dispatcher
        .catch {
            // Handle exceptions, e.g., network error
            System.err.println("Error fetching movies: ${it.message}")
            emit(emptyList<Movie>()) // Emit an empty list in case of an error
        }
}