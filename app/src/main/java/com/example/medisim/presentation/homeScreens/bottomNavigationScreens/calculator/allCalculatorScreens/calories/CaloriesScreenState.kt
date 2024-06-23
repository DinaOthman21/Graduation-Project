package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.calculator.allCalculatorScreens.calories

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString


enum class WorkType {
    Light, Moderate, Heavy
}
data class CaloriesScreenState(
    val weight:Int = 70,
    val dialogState:Boolean = false,
    val isMale:Boolean = true,
    val workType: WorkType? = null,
    val result: AnnotatedString = buildAnnotatedString {append("") }
)
