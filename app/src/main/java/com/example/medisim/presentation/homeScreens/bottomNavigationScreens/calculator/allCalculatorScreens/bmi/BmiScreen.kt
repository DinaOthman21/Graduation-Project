package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.calculator.allCalculatorScreens.bmi


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.medisim.R
import com.example.medisim.presentation.components.AnimatedTextWithTileModes
import com.example.medisim.presentation.components.BackIcon
import com.example.medisim.presentation.components.ButtonClickOn
import com.example.medisim.presentation.components.CalculatorDialogContent
import com.example.medisim.presentation.components.ResultPredictionDialog
import com.example.medisim.ui.theme.CommonComponent2
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@Composable
fun BmiScreen(navController: NavHostController) {
    val vm:BmiViewModel = viewModel()
    val state = vm.state.value

    val context = LocalContext.current
    var showMyComposable by remember { mutableStateOf(false) }


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 12.dp,
                    end = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackIcon{ navController.popBackStack()}
            Spacer(modifier = Modifier.width(40.dp))
            Text(
                text = stringResource(R.string.body_mass_index),
                modifier = Modifier.padding(top = 10.dp, bottom = 30.dp),
                style = TextStyle(
                    fontSize = 26.sp, color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                ),
            )
        }

        AnimatedTextWithTileModes(stringResource(R.string.height_cm))


        NumberPicker(modifier = Modifier.padding(vertical = 15.dp), number = 300){
            vm.onHeightSelected(it)
        }

        AnimatedTextWithTileModes(stringResource(R.string.weight_kg))


        NumberPicker(modifier = Modifier.padding(vertical = 15.dp), initialFactor = 3,number = 210){
            vm.onWeightSelected(it)
        }
        Spacer(modifier = Modifier.weight(1f))
        ButtonClickOn(
            buttonText = stringResource(R.string.calculate),
            modifier = Modifier.padding(bottom = 15.dp),
            paddingValue = 0
        ){
            showMyComposable = true
        }


        if (showMyComposable) {
            vm.OnCalcBMI(context)
            showMyComposable = false
        }



        AnimatedVisibility(visible = state.dialogState) {
            ResultPredictionDialog(content = {
                CalculatorDialogContent(state.result){
                    vm.onDialogClosed()
                }
            }, image = R.drawable.mbi_logo) {
                vm.onDialogClosed()
            }
        }
    }

}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NumberPicker(modifier: Modifier = Modifier,number:Int,initialFactor:Int=2,onSelectNumber:(Int)->Unit) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val contentPadding = (maxWidth - 50.dp) / 2
        val offSet = maxWidth / 5
        val itemSpacing = offSet - 50.dp
        val pagerState = rememberPagerState(
            initialPage = number/initialFactor,
            pageCount = { number }
        )

        val scope = rememberCoroutineScope()

        val mutableInteractionSource = remember {
            MutableInteractionSource()
        }


        CenterCircle(
            modifier = modifier
                .size(50.dp)
                .align(Alignment.Center),
            fillColor = CommonComponent2,
            strokeWidth = 2.dp
        )


        LaunchedEffect(pagerState) {
            // Collect from the a snapshotFlow reading the currentPage
            snapshotFlow { pagerState.settledPage }.collect { page ->
               // Toast.makeText(context,"$page",Toast.LENGTH_SHORT).show()
                onSelectNumber(page)
            }
        }

        HorizontalPager(
            modifier = modifier,
            state = pagerState,
            flingBehavior = PagerDefaults.flingBehavior(
                state = pagerState,
                pagerSnapDistance = PagerSnapDistance.atMost(0)
            ),
            contentPadding = PaddingValues(horizontal = contentPadding),
            pageSpacing = itemSpacing,
        ) { page ->
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .graphicsLayer {
                        val pageOffset = ((pagerState.currentPage - page) + pagerState
                            .currentPageOffsetFraction).absoluteValue
                        // Set the item alpha based on the distance from the center
                        val percentFromCenter = 1.0f - (pageOffset / (5f / 2f))
                        val opacity = 0.25f + (percentFromCenter * 0.75f).coerceIn(0f, 1f)

                        alpha = opacity
                        clip = true
                    }
                    .clickable(
                        interactionSource = mutableInteractionSource,
                        indication = null,
                        enabled = true,
                    ) {
                        scope.launch {
                            pagerState.animateScrollToPage(page)
                        }
                    }) {
                Text(
                    text = "$page",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(50.dp)
                        .wrapContentHeight(),
                    textAlign = TextAlign.Center
                )
            }
        }



    }
}


@Composable
fun CenterCircle(
    modifier: Modifier = Modifier,
    fillColor: Color,
    strokeWidth: Dp
) {
    Canvas(
        modifier = modifier
            .size(75.dp)
    ) {

        drawArc(
            color = fillColor,
            0f,
            360f,
            true,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )
    }
}


