package com.example.tmdbapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/search/movie?api_key=2810b46c0fe82e2e7eb43466581d495f&language=en-US") // TMDB API
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}
