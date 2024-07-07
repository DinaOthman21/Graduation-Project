package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medisim.R
import com.example.medisim.presentation.components.ScreenLazyRow
import com.example.medisim.presentation.components.ShimmerPostsList
import com.example.medisim.presentation.components.TextWithBoldUnderLine
import com.example.medisim.presentation.components.VerticalAvoidCard
import com.example.medisim.presentation.navigation.Screens


@Composable
fun HomeScreen(navController: NavHostController,homeViewModel: HomeViewModel) {
    val posts = homeViewModel.postsList.collectAsState().value

    if(posts.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.padding(top = 10.dp)
        ){

            item{
                Column {
                    // make title for "Advices" with small bold under line
                    if (posts.filter { it.isAdvice }.isNotEmpty()){
                        TextWithBoldUnderLine(
                            text = stringResource(R.string.advices),
                            lineColor  = MaterialTheme.colorScheme.onSecondary
                        )
                    }


                    // this for the Horizontal Advices posts
                    ScreenLazyRow(posts = posts.filter { it.isAdvice }){post->
                        // on user click on post to show its details
                        // navController.currentBackStackEntry?.arguments?.putParcelable("user", user) // old
                        navController.currentBackStackEntry?.savedStateHandle?.set("post", post) // new
                        navController.navigate(Screens.PostDetails.route)

                    }

                    // also make title for "Avoid" with small bold under line
                    if (posts.filter { it.isAdvice.not() }.isNotEmpty()){
                        TextWithBoldUnderLine(
                            text = stringResource(R.string.avoid),
                            lineColor  = MaterialTheme.colorScheme.onSecondary
                        )
                    }

                }
            }

            // this items for Avoid posts
            items(posts.filter { it.isAdvice.not() }){
                VerticalAvoidCard(it){post->
                    // on user click on post to show its details
                    navController.currentBackStackEntry?.savedStateHandle?.set("post", post) // new
                    navController.navigate(Screens.PostDetails.route)

                }
            }
        }
    }else{
        ShimmerPostsList()
    }


}