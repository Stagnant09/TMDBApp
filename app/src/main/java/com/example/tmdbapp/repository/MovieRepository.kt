package com.example.tmdbapp.repository

import android.util.Log
import com.example.tmdbapp.network.ApiResult
import com.example.tmdbapp.network.MovieApi
import com.example.tmdbapp.network.MovieResponse
import com.example.tmdbapp.network.RetrofitProvider
import com.example.tmdbapp.transformers.toMovies

object MovieRepository {
    private val api = RetrofitProvider.retrofit.create(MovieApi::class.java)

    suspend fun fetchMovies(query: String, page: Int): List<MovieResponse> {
        val result = api.getMovies(query, page).results
        result.forEach {
            Log.d("MovieRepository", "RAW posterPath=${it.posterPath}")
        }
        return result
    }
}

fun ApiResult<MovieResponse>.toMovies(): List<MovieResponse> = results
