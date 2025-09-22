package com.example.tmdbapp.network

import com.example.tmdbapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    const val API_KEY = BuildConfig.TMDB_API_KEY

    private val loggingInterceptor = HttpLoggingInterceptor { message ->
        android.util.Log.d("API_LOG", message)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Custom interceptor for more structured logging
    private val customLoggingInterceptor = Interceptor { chain ->
        val request = chain.request()
        val t1 = System.nanoTime()

        android.util.Log.d("API_LOG", "--> ${request.method} ${request.url}")
        request.headers.forEach { header ->
            android.util.Log.d("API_LOG", "Header: ${header.first} = ${header.second}")
        }

        val response = chain.proceed(request)

        val t2 = System.nanoTime()
        val durationMs = (t2 - t1) / 1_000_000

        val responseBody = response.body
        val responseString = responseBody?.string()

        android.util.Log.d("API_LOG", "<-- ${response.code} ${response.message} (${durationMs}ms)")
        android.util.Log.d("API_LOG", "Response Body: $responseString")

        // Re-create response body because .string() consumes it
        response.newBuilder()
            .body(ResponseBody.create(responseBody?.contentType(), responseString ?: ""))
            .build()
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(customLoggingInterceptor) // Logs nicely formatted request/response
        .addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            val requestBuilder = original.newBuilder().url(url)
            chain.proceed(requestBuilder.build())
        }
        .addInterceptor(loggingInterceptor) // OkHttp's built-in body logger
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
