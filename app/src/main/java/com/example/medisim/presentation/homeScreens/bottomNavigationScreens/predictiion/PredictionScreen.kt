package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medisim.R
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.disease.*
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.skinDisease.*
import com.example.medisim.presentation.navigation.Screens
import com.example.medisim.ui.theme.CommonComponent2
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun PredictionScreen(
    predictionViewModel: PredictionViewModel,
    skinDiseaseViewModel: SkinDiseaseScreenViewModel
) {

    val tabItemRoute = listOf(
        Tab(nameId = (R.string.disease)),
        Tab(nameId = (R.string.skin_disease))
    )
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    val unSelectedTabColor = MaterialTheme.colorScheme.background

    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .background(color = Color.Transparent),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .pagerTabIndicatorOffset(
                            pagerState,
                            tabPositions
                        )
                        .height(2.dp),
                    color = CommonComponent2
                )
            },
            backgroundColor = unSelectedTabColor
        ) {
            tabItemRoute.forEachIndexed { index, screen ->
                val color = remember {
                    Animatable(unSelectedTabColor)
                }
                LaunchedEffect(pagerState.currentPage == index) {
                    color.animateTo(unSelectedTabColor)
                }
                Tab(
                    text = {
                        Text(
                            stringResource(screen.nameId),
                            style = if (pagerState.currentPage == index) TextStyle(
                                color = CommonComponent2,
                                fontSize = 22.sp,
                            )
                            else TextStyle(
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 20.sp
                            )
                        )
                    },
                    selected = pagerState.currentPage == index,
                    modifier = Modifier.background(
                        color = color.value,
                    ),
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }

                    })
            }

        }

        HorizontalPager(
            count = tabItemRoute.size, state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            verticalAlignment = Alignment.Top,

            ) { index ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                if (index==0){
                    DiseasePredictionScreen(predictionViewModel)
                }else{
                    SkinDiseaseScreen(skinDiseaseViewModel = skinDiseaseViewModel)
                }
            }
        }


    }


}

