package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.calculator.allCalculatorScreens.bmi

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

data class BmiScreenState(
    val weight:Int = 110,
    val height:Int = 110,
    val dialogState:Boolean = false,
    val result:AnnotatedString = buildAnnotatedString {append("") }
)
