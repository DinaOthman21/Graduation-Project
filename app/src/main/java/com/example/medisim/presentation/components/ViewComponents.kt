package com.example.medisim.presentation.components



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medisim.R

val images = listOf(
    "https://images.unsplash.com/photo-1599918805559-23cb83102f74?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1661167205913-4aeaa66a0445?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1620015092538-e33c665fc181?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://plus.unsplash.com/premium_photo-1661887262365-1d6a1cf3da22?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1693108034943-fc8f19e7573c?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1609955548274-d1f3f13519b8?q=80&w=1965&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1599918805559-23cb83102f74?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1661167205913-4aeaa66a0445?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1620015092538-e33c665fc181?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://plus.unsplash.com/premium_photo-1661887262365-1d6a1cf3da22?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1693108034943-fc8f19e7573c?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1609955548274-d1f3f13519b8?q=80&w=1965&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
)


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MainAdvicesAndAvoidScreen() {

    LazyColumn(){
        item{
            Column {
                // make title for "Advices" with small bold under line
                TextWithBoldUnderLine(
                    text = stringResource(R.string.advices),
                    lineColor  = MaterialTheme.colorScheme.onSecondary
                )

                // this for the Horizontal Advices posts
                ScreenLazyRow(images)

                // also make title for "Avoid" with small bold under line
                TextWithBoldUnderLine(
                    text = stringResource(R.string.avoid),
                    lineColor  = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
        // this items for Avoid posts
        items(images){
            VerticalAvoidCard(it)
        }
    }
}



@Composable
fun ScreenLazyRow(images: List<String>) {
    Column {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 16.dp)
        ) {

            items(images) { image->
                HorizontalAdviceCard(image)
            }

        }

    }
}

@Composable
fun HorizontalAdviceCard(image:String) {
    Box ( modifier = Modifier
        .width(300.dp)
        .height(220.dp)
        .padding(horizontal = 6.dp)
        .clip(RoundedCornerShape(16.dp))
        .clickable {
            // here when user click on Advice post
        }
    )
    {

        ViewImage(
            image = image,
            contentDescription ="Advice image"
        )

        Box(modifier  = Modifier
            .width(300.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .align(Alignment.BottomStart)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.DarkGray,
                        Color.Transparent,
                        Color.DarkGray,
                    )
                )
            ),
            contentAlignment = Alignment.Center
        ){
            TextTitle(
                text = "Post title here",
                textFont = 24,
                textFontWight = FontWeight.Bold,
                textColor = Color.White
            )
        }


    }
}


@Composable
fun VerticalAvoidCard(image:String) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .shadow(elevation = 24.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                // here when user click on Avoid post
            }
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onBackground)

        ){
            ViewImage(
                image = image,
                modifier = Modifier
                    .weight(0.5f)
                    .height(160.dp),
                contentDescription = "Avoid image"
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(end = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ){

                TextTitle(
                    text = "Post title here",
                    modifier = Modifier.padding(top = 10.dp),
                    textFontWight = FontWeight.Bold,
                    textColor = MaterialTheme.colorScheme.primary
                )
                TextTitle(
                    text = "Post description here and all content will be here ew kalam keter awy 3shan ana msh fady, Post description here and all content will be here ew kalam keter awy 3shan ana msh fady",
                    modifier = Modifier.padding(top = 8.dp),
                    textFont = 16,
                    textFontWight = FontWeight.Bold,
                    textColor = MaterialTheme.colorScheme.secondary,
                    maxLines = 4,
                )

            }

        }
    }
}


