package com.example.medisim.presentation.components



import android.util.Log
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.medisim.data.remote.dto.main.Post
import kotlinx.coroutines.delay


@Composable
fun ScreenLazyRow(posts: List<Post>, onPostClick:(Post)->Unit) {
    val listState = rememberLazyListState()


    Column {
        LazyRow(
            state = listState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 16.dp)
        ) {
            items(posts) { post->
                HorizontalAdviceCard(post){
                    onPostClick(it)
                }

            }

        }


    }
    LaunchedEffect(Unit) {
        while (true){
            Log.d("TAG",">>>>>>>>>>>>>>>>>>>>>>>>> index ${(listState.firstVisibleItemIndex+1) }<<<<<<<<<<<<<<<<<<<<<<<<<")
            Log.d("TAG",">>>>>>>>>>>>>>>>>>>>>>>>> size ${(posts.size-1) }<<<<<<<<<<<<<<<<<<<<<<<<<")
            Log.d("TAG",">>>>>>>>>>>>>>>>>>>>>>>>> mod ${(listState.firstVisibleItemIndex+1)%(posts.size-1) }<<<<<<<<<<<<<<<<<<<<<<<<<")
            listState.animateScrollToItem((listState.firstVisibleItemIndex+1)%(posts.size-1))
            delay(3000)
        }
    }


}





@Composable
fun HorizontalAdviceCard(post: Post,onPostClick:(Post)->Unit) {
    val context = LocalContext.current
    // Now can access resources using the context
    val resources = context.resources
    val isArabicLang = resources.configuration.locales[0].language == "ar"

    Box ( modifier = Modifier
        .width(300.dp)
        .height(220.dp)
        .padding(horizontal = 6.dp)
        .clip(RoundedCornerShape(16.dp))
        .clickable {
            // here when user click on Advice post
            onPostClick(post)
        }
    )
    {

        ViewImage(
            image = post.imgLink,
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
                        Color.DarkGray.copy(0.7f),
                        Color.Gray.copy(0.4f),
                        Color.DarkGray.copy(0.7f),
                    )
                )
            ),
            contentAlignment = Alignment.Center
        ){
            TextTitle(
                text = if (isArabicLang) post.arTitle else post.enTitle,
                textFont = 24,
                textFontWight = FontWeight.Bold,
                textColor = Color.White
            )
        }


    }
}


@Composable
fun VerticalAvoidCard(post: Post,onPostClick:(Post)->Unit) {
    val context = LocalContext.current
    // Now can access resources using the context
    val resources = context.resources
    val isArabicLang = resources.configuration.locales[0].language == "ar"

    Card(
        modifier = Modifier
            .padding(10.dp)
            .shadow(elevation = 24.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                // here when user click on Avoid post
                onPostClick(post)
            }
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onBackground)

        ){
            ViewImage(
                image = post.imgLink,
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
                    text =  if (isArabicLang) post.arTitle else post.enTitle,
                    modifier = Modifier.padding(top = 10.dp),
                    textFontWight = FontWeight.Bold,
                    textColor = MaterialTheme.colorScheme.primary
                )
                TextTitle(
                    text =  if (isArabicLang) post.arDescription else post.enDescription,
                    modifier = Modifier.padding(top = 8.dp),
                    textFont = 16,
                    textFontWight = FontWeight.Bold,
                    textColor = MaterialTheme.colorScheme.secondary,
                    maxLines = 4,
                    isJustify = false
                )

            }

        }
    }
}





