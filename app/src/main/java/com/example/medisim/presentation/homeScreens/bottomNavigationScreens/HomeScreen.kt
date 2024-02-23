package com.example.medisim.presentation.homeScreens.bottomNavigationScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medisim.R
import com.example.medisim.presentation.components.ScreenLazyRow
import com.example.medisim.presentation.components.TextWithBoldUnderLine
import com.example.medisim.presentation.components.VerticalAvoidCard
import com.example.medisim.presentation.components.images

@Composable
fun HomeScreen(appNavController: NavHostController) {

    LazyColumn(
        modifier = Modifier.padding(top = 10.dp)
    ){
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