package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.calculator.allCalculatorScreens.bmi

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

data class BmiScreenState(
    val weight:Int = 70,
    val height:Int = 150,
    val dialogState:Boolean = false,
    val result:AnnotatedString = buildAnnotatedString {append("") }
)
