package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.disease

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.medisim.R
import com.example.medisim.presentation.components.ButtonClickOn
import com.example.medisim.presentation.components.CustomChip
import com.example.medisim.presentation.components.DropdownMenuExample
import com.example.medisim.presentation.components.LottieAnimationShow
import com.example.medisim.presentation.components.PredictionDialogContent
import com.example.medisim.presentation.components.ResultPredictionDialog

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
            .fillMaxSize()
    ){
//        AnimatedVisibility(visible = listOfSelectedSymptoms.isEmpty()) {
//            Row (
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center
//            ){
//                LottieAnimationShow(
//                    animationResId = R.raw.predic,
//                    size = 200,
//                    padding = 0,
//                    paddingBottom = 0
//                )
//            }
//        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            LottieAnimationShow(
                animationResId = R.raw.predic,
                size = 150,
                padding = 0,
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
        FlowRow(
            modifier = Modifier
        ){
            for (symptomItem in listOfSelectedSymptoms){
                CustomChip(text = if (isArabicLang) symptomItem.arName else symptomItem.enName) {
                    predictionViewModel.deleteFromSelectedList(symptomItem)
                }
            }
        }
        // selected items here
        Spacer(modifier = Modifier.weight(1f))
        ButtonClickOn(
            buttonText = stringResource(R.string.predict),
            modifier = Modifier.padding(bottom = 10.dp),
            paddingValue = 0
        ) {
            if (listOfSelectedSymptoms.isEmpty()){
                Toast.makeText(context, context.getString(R.string.please_select_your_symptoms),
                    Toast.LENGTH_SHORT).show()
            }else{
                predictionViewModel.onPredictClick()
            }

        }

        AnimatedVisibility(visible = state.dialogState) {
           state.predictionDiseaseResponse?.let {
               ResultPredictionDialog(content = {
                   PredictionDialogContent(
                      it
                   ) {
                       predictionViewModel.onDialogDismiss()
                   }
               }, image = R.drawable.human_ai) {
                   predictionViewModel.onDialogDismiss()
               }
           }
        }

    }
}