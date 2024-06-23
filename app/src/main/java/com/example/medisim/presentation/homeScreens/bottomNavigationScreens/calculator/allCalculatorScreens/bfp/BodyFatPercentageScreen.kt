package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.calculator.allCalculatorScreens.bfp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.medisim.R
import com.example.medisim.presentation.components.AnimatedTextWithTileModes
import com.example.medisim.presentation.components.BackIcon
import com.example.medisim.presentation.components.BfpCalculatorDialogContent
import com.example.medisim.presentation.components.ButtonClickOn
import com.example.medisim.presentation.components.CheckboxWithName
import com.example.medisim.presentation.components.ResultPredictionDialog
import com.example.medisim.presentation.components.TextLabel
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.calculator.allCalculatorScreens.bmi.NumberPicker


@Composable
fun BodyFatPercentageScreen(navController: NavHostController) {
    val vm: BodyFatViewModel = viewModel()
    val state = vm.state.value

    val context = LocalContext.current


    Column (
        modifier = Modifier
            .fillMaxSize(),
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
                    fontSize = 30.sp, color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                ),
            )
        }

        AnimatedTextWithTileModes(stringResource(R.string.height_cm))


        NumberPicker(modifier = Modifier.padding(vertical = 25.dp), number = 300){
            vm.onHeightSelected(it)
        }

        AnimatedTextWithTileModes(stringResource(R.string.weight_kg))


        NumberPicker(modifier = Modifier.padding(vertical = 35.dp), initialFactor = 3, number = 210){
            vm.onWeightSelected(it)
        }

        AnimatedTextWithTileModes(stringResource(id = R.string.age))


        NumberPicker(modifier = Modifier.padding(vertical = 35.dp),number = 100, initialFactor = 5){
            vm.onAgeSelected(it)
        }

        TextLabel(
            text = stringResource(R.string.gender),
            modifier = Modifier.padding(bottom = 10.dp),
            textFont = 18,
            textFontWight = FontWeight.Bold
        )

        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            CheckboxWithName(
                checkBoxText = stringResource(R.string.male),
                checkedState = state.isMale,
                onToggleClick = {vm.onGenderSelected(true)}
            )
            CheckboxWithName(
                checkBoxText = stringResource(R.string.female),
                checkedState = state.isMale.not(),
                onToggleClick = {vm.onGenderSelected(false)}
            )

        }



        Spacer(modifier = Modifier.weight(1f))
        ButtonClickOn(
            buttonText = stringResource(R.string.calculate),
            modifier = Modifier.padding(bottom = 15.dp),
            paddingValue = 0
        ) {
            vm.onCalcBFP(context)
        }




        AnimatedVisibility(visible = state.dialogState) {
            ResultPredictionDialog(content = {
                BfpCalculatorDialogContent(state.result){
                    vm.onDialogClosed()
                }
            }, image = R.drawable.body_logo) {
                vm.onDialogClosed()
            }
        }
    }

}