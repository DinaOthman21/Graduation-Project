package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medisim.R
import com.example.medisim.presentation.components.ImageButtonWithText
import com.example.medisim.presentation.components.LottieAnimationShow
import com.example.medisim.presentation.navigation.Screens


@Composable
fun MainCalculatorScreen(appNavController: NavHostController) {


    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
      item {
          Row (
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.Center
          ){
              LottieAnimationShow(
                  animationResId = R.raw.calculatoranimation,
                  size = 180,
                  padding = 12,
                  paddingBottom = 0
              )

          }
          ImageButtonWithText(
              image = R.drawable.body,
              text = stringResource(R.string.body_fat_percentage)
          ){
              appNavController.navigate(Screens.BfpCalculator.route)

          }
          ImageButtonWithText(
              image = R.drawable.bmi,
              text = stringResource(R.string.body_mass_index)
          ){
              appNavController.navigate(Screens.BmiCalculator.route)
          }

          ImageButtonWithText(
              image = R.drawable.calorie,
              text = stringResource(R.string.daily_calories_need)
          ){
              appNavController.navigate(Screens.CaloriesCalculator.route)
          }

          ImageButtonWithText(
              image = R.drawable.blood_volume,
              text = stringResource(R.string.estimated_blood_volume)
          ){
              appNavController.navigate(Screens.BvCalculator.route)
          }
      }
    }
}