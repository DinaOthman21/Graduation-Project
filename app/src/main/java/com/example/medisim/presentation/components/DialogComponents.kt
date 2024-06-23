package com.example.medisim.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.medisim.R
import com.example.medisim.data.remote.dto.main.PredictionDisease
import com.example.medisim.ui.theme.CommonComponent2
import com.example.medisim.ui.theme.brush2
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


@Composable
fun ResultPredictionDialog(
    content: @Composable () -> Unit,
    image:Int,
    height:Int = 90,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 30.dp)
        ) {
            content()
            // image here
            Image(
                painter = painterResource(id = image),
                modifier = Modifier
                    .height(height.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .align(Alignment.TopCenter)
                ,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

        }
    }
}


@OptIn(ExperimentalLayoutApi::class, ExperimentalPagerApi::class)
@Composable
fun PredictionDialogContent(
    predictionDiseaseResponse: List<PredictionDisease>,
    onDismiss: () -> Unit
) {
    val pagerState = rememberPagerState()

    val context = LocalContext.current
    // Now can access resources using the context
    val resources = context.resources
    val isArabicLang = resources.configuration.locales[0].language == "ar"

    Column(
        modifier = Modifier
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .background(
                    brush = brush2,
                    shape = RoundedCornerShape(25.dp, 10.dp, 25.dp, 10.dp)
                )

        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )  {
                item {
                    Spacer(modifier = Modifier.height(40.dp))


                    HorizontalPager(
                        count = predictionDiseaseResponse.size,
                        state = pagerState,
                        ) { index ->
                        Column {
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = Color.White)) {
                                        append(stringResource(R.string.based_on_the_provided_symptoms_the_diagnosed_condition_is))
                                    }
                                    withStyle(style = SpanStyle(color =  Color(0xFF05C6F5))) {
                                        append(if (isArabicLang) predictionDiseaseResponse[index].arDiseaseName
                                        else predictionDiseaseResponse[index].enDiseaseName)
                                    }
                                    withStyle(style = SpanStyle(color = Color.White)) {
                                        append(", with ")
                                    }
                                    withStyle(style = SpanStyle(color =  Color(0xFF05C6F5)
                                    )) {
                                        append("${predictionDiseaseResponse[index].confidence}%")
                                    }
                                    withStyle(style = SpanStyle(color = Color.White)) {
                                        append(" confidence.")
                                    }

                                },
                                color = MaterialTheme.colorScheme.primary,
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = Color.White)) {
                                        append(stringResource(R.string.detailed_description)  )
                                    }
                                    withStyle(style = SpanStyle(color = Color(0xFF05C6F5))) {
                                        append(if (isArabicLang) predictionDiseaseResponse[index].arDiseaseDescription
                                        else predictionDiseaseResponse[index].enDiseaseDescription)
                                    }

                                },
                                color = MaterialTheme.colorScheme.primary,
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = Color.White)) {
                                        append(stringResource(R.string.as_a_follow_up_please_consider_the_following_recommendations))
                                    }

                                },
                                color = MaterialTheme.colorScheme.primary,
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            FlowRow{
                                for (advice in if (isArabicLang) predictionDiseaseResponse[index].arAdvices
                                else predictionDiseaseResponse[index].enAdvices){
                                    TextLabel(
                                        text = "  $advice  ",
                                        textColor =  Color(0xFF05C6F5),
                                        textFont = 16
                                    )
                                }

                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ){
                                predictionDiseaseResponse.indices.forEach {
                                    Box(modifier = Modifier
                                        .size(15.dp)
                                        .clip(CircleShape)
                                        .shadow(12.dp, CircleShape)
                                        .padding(4.dp)
                                        .background(if (it == index) CommonComponent2 else MaterialTheme.colorScheme.secondary)
                                    )
                                }
                            }

                        }


                    }





                    Spacer(modifier = Modifier.height(24.dp))
                    ButtonClickOn(buttonText = stringResource(R.string.ok), paddingValue = 0) {
                        onDismiss()
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                }



            }

        }
    }
}


@Composable
fun SkinDialogContent(
    diseaseName: String,
    diseaseDescription: String,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .background(
                    brush = brush2,
                    shape = RoundedCornerShape(25.dp, 10.dp, 25.dp, 10.dp)
                )
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )  {
                item {
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.White)) {
                                append(stringResource(R.string.based_on_the_observed_skin_symptoms_the_identified_dermatological_condition_is))
                            }
                            withStyle(style = SpanStyle(color =  Color(0xFF05C6F5))) {
                                append(diseaseName)
                            }

                        },
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.White)) {
                                append(stringResource(R.string.detailed_description)  )
                            }
                            withStyle(style = SpanStyle(color = Color(0xFF05C6F5))) {
                                append(diseaseDescription)
                            }

                        },
                        color = MaterialTheme.colorScheme.primary,
                    )

                    Spacer(modifier = Modifier.height(40.dp))
                    ButtonClickOn(buttonText = stringResource(R.string.ok), paddingValue = 0) {
                        onDismiss()
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                }



            }
        }
    }

}



@Composable
fun MedicalTestDialogContent(
    result: String,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(25.dp, 10.dp, 25.dp, 10.dp)
                )
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )  {
                item {
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = buildAnnotatedString {

                            withStyle(style = SpanStyle(color =  Color(0xFF05C6F5))) {
                                append(result)
                            }

                        },
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    ButtonClickOn(buttonText = stringResource(R.string.ok), paddingValue = 0) {
                        onDismiss()
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                }



            }
        }
    }

}









@Composable
fun BmiCalculatorDialogContent(
    result: AnnotatedString,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .background(
                    brush = brush2,
                    shape = RoundedCornerShape(25.dp, 10.dp, 25.dp, 10.dp)
                )
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )  {
                item {
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = result,
                        color = MaterialTheme.colorScheme.primary,
                    )

                    Spacer(modifier = Modifier.height(40.dp))
                    ButtonClickOn(buttonText = stringResource(R.string.ok), paddingValue = 0) {
                        onDismiss()
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                }



            }
        }
    }

}



@Composable
fun BfpCalculatorDialogContent(
    result: AnnotatedString,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .background(
                    brush = brush2,
                    shape = RoundedCornerShape(25.dp, 10.dp, 25.dp, 10.dp)
                )
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )  {
                item {
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = result+ AnnotatedString("\n"),
                        color = MaterialTheme.colorScheme.primary,
                    )

                    Image(
                        painter = painterResource(id = R.drawable.body_fat_man),
                        modifier = Modifier
                            .fillMaxSize()
                        ,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(40.dp))
                    ButtonClickOn(buttonText = stringResource(R.string.ok), paddingValue = 0) {
                        onDismiss()
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                }



            }
        }
    }

}





@Composable
fun CaloriesCalculatorDialogContent(
    result: AnnotatedString,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .background(
                    brush = brush2,
                    shape = RoundedCornerShape(25.dp, 10.dp, 25.dp, 10.dp)
                )
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )  {
                item {
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = result,
                        color = MaterialTheme.colorScheme.primary,
                    )

                    Spacer(modifier = Modifier.height(40.dp))
                    ButtonClickOn(buttonText = stringResource(R.string.ok), paddingValue = 0) {
                        onDismiss()
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                }



            }
        }
    }

}




