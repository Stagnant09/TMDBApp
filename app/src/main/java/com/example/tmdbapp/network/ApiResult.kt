package com.example.tmdbapp.network

data class ApiResult<T>(
    val page: Int,
    val results: List<T>
)