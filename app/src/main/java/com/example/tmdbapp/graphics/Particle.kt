package com.example.tmdbapp.graphics

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class Particle(
    var position: Offset,
    var velocity: Offset,
    val color: Color,
    val radius: Float
)