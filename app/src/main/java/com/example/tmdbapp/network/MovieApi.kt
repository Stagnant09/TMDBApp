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
    val genreIds: List<Int>?,

    val id: Int,

    @SerialName("original_language")
    val originalLanguage: String?,

    @SerialName("original_title")
    val originalTitle: String?,

    val overview: String,
    val popularity: Double,

    @SerialName("poster_path")
    val poster_path: String,

    @SerialName("release_date")
    val releaseDate: String?,

    val title: String,
    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Int
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