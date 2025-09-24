package com.example.tmdbapp.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalCustomColors = staticCompositionLocalOf<CustomColors> {
    error("No CustomColors provided")
}

data class CustomColors(
    val particleColor: Color,
    val starColor: Color
)

fun lightCustomColors() = CustomColors(
    particleColor = Color(red = 180, green = 180, blue = 180).copy(alpha = 0.7f),
    starColor = Color(0xFFFFC107)
)

fun darkCustomColors() = CustomColors(
    particleColor = Color(red = 200, green = 200, blue = 200).copy(alpha = 0.7f),
    starColor = Color(0xFFFFC107)
)