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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medisim.R
import com.example.medisim.presentation.components.ImageButtonWithText
import com.example.medisim.presentation.components.LottieAnimationShow


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainCalculatorScreen() {

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
                  animationResId = R.raw.calculator_animation,
                  size = 180,
                  padding = 12,
                  paddingBottom = 0
              )

          }
          ImageButtonWithText(
              image = R.drawable.body,
              text = stringResource(R.string.body_fat_percentage)
          ){}
          ImageButtonWithText(
              image = R.drawable.blood_volume,
              text = stringResource(R.string.estimated_blood_volume)
          ){}
          ImageButtonWithText(
              image = R.drawable.creatinine,
              text = stringResource(R.string.creatinine_clearance)
          ){}
          ImageButtonWithText(
              image = R.drawable.body,
              text = stringResource(R.string.body_fat_percentage)
          ){}

      }
    }
}