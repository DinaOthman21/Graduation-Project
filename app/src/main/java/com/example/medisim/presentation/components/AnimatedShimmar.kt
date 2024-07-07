package com.example.medisim.presentation.components




import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medisim.R
import com.example.medisim.ui.theme.DarkComponentColor2
import androidx.compose.ui.res.stringResource
import com.example.medisim.ui.theme.CommonComponent2


@Composable
fun animatedShimmer(): Brush {
    val shimmerColors = listOf(
        DarkComponentColor2.copy(alpha = 0.6f),
        CommonComponent2.copy(alpha = 0.2f),
        DarkComponentColor2.copy(alpha = 0.6f),

        )

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

@Composable
fun ShimmerPostsList() {

    val brush = animatedShimmer()
        LazyColumn(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ){
            item{
                // make title for "Advices" with small bold under line
                TextWithBoldUnderLine(
                    text = stringResource(R.string.advices),
                    lineColor  = MaterialTheme.colorScheme.onSecondary
                )
                LazyRow {
                    items(10){
                        ShimmerHorizontalImageCard(brush = brush)
                    }
                }
                // also make title for "Avoid" with small bold under line
                TextWithBoldUnderLine(
                    text = stringResource(R.string.avoid),
                    lineColor  = MaterialTheme.colorScheme.onSecondary
                )
            }
            items(10){ _ ->
                ShimmerImageCard(brush = brush)
            }
    }
}

@Composable
fun ShimmerImageCard(brush: Brush) {

    Spacer(
        modifier = Modifier
            .height(160.dp)
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(
                elevation = 16.dp,
                spotColor = DarkComponentColor2,
                shape = RoundedCornerShape(16.dp)
            )
            .background(MaterialTheme.colorScheme.onTertiary)
            .background(brush)

    )

}

@Composable
fun ShimmerHorizontalImageCard(brush: Brush) {

    Spacer(
        modifier = Modifier
            .width(300.dp)
            .height(220.dp)
            .padding(6.dp)
            .shadow(
                elevation = 16.dp,
                spotColor = DarkComponentColor2,
                shape = RoundedCornerShape(16.dp)
            )
            .background(MaterialTheme.colorScheme.onTertiary)
            .background(brush)

    )

}




@Composable
@Preview(showBackground = true)
fun ShimmerGridItemPreview() {
    ShimmerPostsList()
}

