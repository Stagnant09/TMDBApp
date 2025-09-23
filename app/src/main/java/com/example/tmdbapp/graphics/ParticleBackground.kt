package com.example.tmdbapp.graphics


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

@Composable
fun ParticleBackground(particleCount: Int = 50) {
    val canvasWidth = remember { mutableStateOf(0f) }
    val canvasHeight = remember { mutableStateOf(0f) }

    // Particles as state objects so Compose sees changes
    val particles = remember { mutableStateListOf<Particle>() }

    Canvas(modifier = Modifier.fillMaxSize()) {
        if (particles.isEmpty()) {
            canvasWidth.value = size.width
            canvasHeight.value = size.height

            repeat(particleCount) {
                particles.add(
                    Particle(
                        position = Offset(Random.nextFloat() * size.width, Random.nextFloat() * size.height),
                        velocity = Offset((Random.nextFloat() - 0.5f) * 2, (Random.nextFloat() - 0.5f) * 2),
                        color = Color.White.copy(alpha = 0.7f),
                        radius = Random.nextFloat() * 2 + 2
                    )
                )
            }
        }

        // Draw particles
        particles.forEach { particle ->
            drawCircle(
                color = particle.color,
                radius = particle.radius,
                center = particle.position
            )
        }
    }

    // Animate particles
    LaunchedEffect(Unit) {
        while (true) {
            withFrameNanos {
                particles.forEachIndexed { index, particle ->
                    var newX = particle.position.x + particle.velocity.x
                    var newY = particle.position.y + particle.velocity.y

                    // Bounce off edges
                    if (newX <= 0 || newX >= canvasWidth.value) particle.velocity = particle.velocity.copy(x = -particle.velocity.x)
                    if (newY <= 0 || newY >= canvasHeight.value) particle.velocity = particle.velocity.copy(y = -particle.velocity.y)

                    // Update particle position
                    particles[index] = particle.copy(
                        position = Offset(
                            x = newX.coerceIn(0f, canvasWidth.value),
                            y = newY.coerceIn(0f, canvasHeight.value)
                        )
                    )
                }
            }
        }
    }
}
