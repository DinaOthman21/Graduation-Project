package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.disease

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.medisim.R
import com.example.medisim.presentation.components.ButtonClickOn
import com.example.medisim.presentation.components.CustomChip
import com.example.medisim.presentation.components.DropdownMenuExample
import com.example.medisim.presentation.components.LottieAnimationShow

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DiseasePredictionScreen(predictionViewModel: PredictionViewModel) {
    val listOfSelectedSymptoms = predictionViewModel.listOfSelectedSymptoms.collectAsState().value
    val dropDownList = predictionViewModel.dropDownList.collectAsState().value

    val state = predictionViewModel.state.value

    val context = LocalContext.current
    // Now can access resources using the context
    val resources = context.resources
    val isArabicLang = resources.configuration.locales[0].language == "ar"


    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ){

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            LottieAnimationShow(
                animationResId = R.raw.predic,
                size = 200,
                padding = 12,
                paddingBottom = 0
            )
        }
        DropdownMenuExample(
            selectedItem = state.editTextSymptom,
            expanded = state.dropDownState,
            items = dropDownList,
            isArabic = isArabicLang,
            onValueChange = {
                newValue -> predictionViewModel.onSymptomNameChange(newValue,isArabicLang)},
            ) {selectedSymptom->
            predictionViewModel.addSymptomToSelectedList(selectedSymptom)
        }
        if (state.predictionDiseaseResponse!=null){

        }else{
            FlowRow{
                for (symptomItem in listOfSelectedSymptoms){
                    CustomChip(text = if (isArabicLang) symptomItem.arName else symptomItem.enName) {
                        predictionViewModel.deleteFromSelectedList(symptomItem)
                    }
                }
            }
        }
        // selected items here
        Spacer(modifier = Modifier.weight(1f))
        ButtonClickOn(
            buttonText = stringResource(R.string.predict),
            modifier = Modifier.padding(bottom = 41.dp),
            paddingValue = 0
        ) {
            predictionViewModel.onPredictClick()
        }

    }
}