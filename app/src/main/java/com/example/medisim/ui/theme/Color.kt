package com.example.medisim.ui.theme

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


// background
val BackgroundDark = Color(0xFF001C30 )
val BackgroundLight = Color(0xFFD6E8EE  )

// text
val TextDark = Color(0xFFD6E8EE)
val TextLight = Color(0xFF001B48)
val TextHint = Color(0xFF8D7F7F)

// components
val ComponentDark = Color(0xFF073C61)
val ComponentLight = Color(0xFF97CADB)
val CommonComponent = Color(0xFF02457A)

// Buttons


@Composable
fun animatedShimmerColor(shimmerColors:List<Color>): Brush {

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )


}
