package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.calculator.allCalculatorScreens.calories

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
import com.example.medisim.presentation.components.ButtonClickOn
import com.example.medisim.presentation.components.CalculatorDialogContent
import com.example.medisim.presentation.components.CheckboxWithName
import com.example.medisim.presentation.components.ResultPredictionDialog
import com.example.medisim.presentation.components.TextLabel
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.calculator.allCalculatorScreens.bmi.NumberPicker


@Composable
fun CaloriesScreen(navController: NavHostController) {
    val vm: CaloriesViewModel = viewModel()
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
                text = stringResource(id = R.string.daily_calories_need),
                modifier = Modifier.padding(top = 10.dp, bottom = 30.dp),
                style = TextStyle(
                    fontSize = 30.sp, color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                ),
            )
        }

        AnimatedTextWithTileModes(stringResource(R.string.weight_kg))


        NumberPicker(modifier = Modifier.padding(vertical = 35.dp), initialFactor = 3, number = 210){
            vm.onWeightSelected(it)
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


        TextLabel(
            text = stringResource(R.string.type_of_work_daily),
            modifier = Modifier.padding(bottom = 10.dp,top = 35.dp),
            textFont = 18,
            textFontWight = FontWeight.Bold
        )
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            CheckboxWithName(
                checkBoxText = stringResource(R.string.light),
                checkedState = if (state.workType==null)false
                else state.workType == WorkType.Light,
                onToggleClick = {vm.onSelectLightWork()}
            )
            CheckboxWithName(
                checkBoxText = stringResource(R.string.moderate),
                checkedState = if (state.workType==null)false
                else state.workType == WorkType.Moderate,
                onToggleClick = {vm.onSelectModerateWork()}
            )
            CheckboxWithName(
                checkBoxText = stringResource(R.string.heavy),
                checkedState = if (state.workType==null)false
                else state.workType == WorkType.Heavy,
                onToggleClick = {vm.onSelectHeavyWork()}
            )

        }



        Spacer(modifier = Modifier.weight(1f))
        ButtonClickOn(
            buttonText = stringResource(R.string.calculate),
            modifier = Modifier.padding(bottom = 15.dp),
            paddingValue = 0
        ) {
            vm.onCalcCalories(context)
        }




        AnimatedVisibility(visible = state.dialogState) {
            ResultPredictionDialog(content = {
               CalculatorDialogContent(state.result){
                    vm.onDialogClosed()
                }
            }, image = R.drawable.calorie_logo2) {
                vm.onDialogClosed()
            }
        }
    }

}