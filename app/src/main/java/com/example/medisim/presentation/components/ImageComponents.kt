package com.example.medisim.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.medisim.ui.theme.HelperColor1
import com.example.medisim.ui.theme.HelperColor2
import com.example.medisim.ui.theme.animatedShimmerColor
import kotlin.random.Random


@Composable
fun ViewImage(
    image:String,
    modifier: Modifier = Modifier,
    contentDescription:String = "",
) {
    val brush = animatedShimmerColor(
        shimmerColors = listOf(
            HelperColor1.copy(alpha = 0.6f),
            HelperColor2.copy(alpha = 0.2f),
            HelperColor1.copy(alpha = 0.6f),
        ))

    AsyncImage(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(brush = brush)
            .shadow(
                elevation = 16.dp,
                spotColor = Color(0xFF000718),
                shape = RoundedCornerShape(16.dp)
            )
        ,
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
    )

}


@Composable
fun ImageButtonClick(
    image:Int,
    modifier: Modifier = Modifier,
    paddingValue:Int ,
    imageWidth: Int = 60,
    imageHeight: Int = 60,
    onButtonClick:() -> Unit
) {
    Image(
        painter = painterResource(id = image),
        modifier = modifier
            .clickable { onButtonClick() }
            .padding(paddingValue.dp)
            .width(imageWidth.dp)
            .height(imageHeight.dp)
        ,
        contentDescription = "image button",
    )

}

@Composable
fun ImageButtonWithText(
    image:Int,
    modifier: Modifier = Modifier,
    text:String,
    textFont: Int = 30,
    height: Int = 150,
    textFontWight: FontWeight = FontWeight.Bold,
    maxLines: Int = 1,
    onButtonClick:() -> Unit
) {


    Surface(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .height(height.dp)
            .padding(top = 10.dp)
            .clickable {
                // here when user click on Advice
                onButtonClick()
            }
    ){
        Image(
            painter = painterResource(id = image),
            modifier = Modifier
                .height(height.dp)
                .background(MaterialTheme.colorScheme.background)
                .clip(RoundedCornerShape(16.dp))
            ,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black,
                        Color.Transparent,
                        Color.Black,
                    )
                )
            ),
            contentAlignment = Alignment.Center,

        ){
            Text(
                text = text,
                modifier = modifier,
                fontSize = textFont.sp,
                fontWeight = textFontWight,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = maxLines,
            )
        }

    }

}







@Composable
fun CircleInitials(
    name: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    size: Int = 70
) {
    val initials = extractInitials(name)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(size.dp)
            .background(color = backgroundColor, shape = CircleShape)
    ) {
        TextLabel(
            text = initials,
            modifier = Modifier.align(Alignment.Center),
            textColor = randomDarkColor(),
            textFont = (size/2)

        )
    }
}


fun randomDarkColor(): Color {
    // Generate random RGB values for darker shades
    val red = Random.nextInt(60,120)
    val green = Random.nextInt(100,140)
    val blue = Random.nextInt(100,140)
    return Color(red, green, blue)
}


fun extractInitials(fullName: String): String {
    return fullName.split(" ")
        .filter { it.isNotEmpty() }
        .mapNotNull { it.firstOrNull()?.uppercase() }
        .joinToString("")
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewCircleInitials() {
    MaterialTheme {
        CircleInitials("Mahmoud Adel")
    }
}
