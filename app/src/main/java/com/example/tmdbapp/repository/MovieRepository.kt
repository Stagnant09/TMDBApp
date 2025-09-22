package com.example.tmdbapp.repository

import com.example.tmdbapp.network.MovieApi
import com.example.tmdbapp.network.MovieResponse
import com.example.tmdbapp.network.RetrofitProvider
import com.example.tmdbapp.transformers.toMovies

object MovieRepository {
    private val api = RetrofitProvider.retrofit.create(MovieApi::class.java)

    suspend fun fetchMovies(query: String, page: Int)  : List<MovieResponse> = api.getMovies(query, page).toMovies()
}
