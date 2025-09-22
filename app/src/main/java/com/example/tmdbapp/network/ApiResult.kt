package com.example.tmdbapp.network

import kotlinx.serialization.Serializable

@Serializable
data class ApiResult<T>(
    val page: Int,
    val results: List<T>,
    val total_pages: Int,
    val total_results: Int
)