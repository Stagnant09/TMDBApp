package com.example.tmdbapp.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Query

@Serializable
data class MovieResponse(
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdrop_path: String?,

    @SerialName("genre_ids")
    val genre_ids: List<Int>?,

    val id: Int,

    @SerialName("original_language")
    val original_language: String?,

    @SerialName("original_title")
    val original_title: String?,

    val overview: String,
    val popularity: Double,

    @SerialName("poster_path")
    val poster_path: String, // Not camelcased because it's a JSON field - not recognized during serialization

    @SerialName("release_date")
    val release_date: String?,

    val title: String,
    val video: Boolean,

    @SerialName("vote_average")
    val vote_average: Double,

    @SerialName("vote_count")
    val vote_count: Int
)

interface MovieApi {
    @GET("search/movie")
    suspend fun getMovies(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("language") language: String = "en-US",
        @Query("include_adult") includeAdult: Boolean = false
    ): ApiResult<MovieResponse>
}