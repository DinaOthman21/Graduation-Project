package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.calculator.allCalculatorScreens.ebv

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

data class BloodVolumeScreenState (
    val weight:Int = 70,
    val dialogState:Boolean = false,
    val age:Int = 20,
    val isMale:Boolean = true,
    val result: AnnotatedString = buildAnnotatedString {append("") }
)