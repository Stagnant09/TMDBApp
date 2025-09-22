package com.example.tmdbapp.transformers

//Converts ApiResult to List<MovieResult>

import com.example.tmdbapp.network.ApiResult
import com.example.tmdbapp.network.MovieResponse

fun ApiResult<List<MovieResponse>>.toMovies(): List<MovieResponse> {
    val results = mutableListOf<MovieResponse>()
    this.results.forEach { movieResponseItem ->
        movieResponseItem.forEach {
            results.add(it)
        }
    }
    return results
}