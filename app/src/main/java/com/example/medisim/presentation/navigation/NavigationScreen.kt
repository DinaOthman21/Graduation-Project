package com.example.medisim.presentation.navigation

import com.example.medisim.R


sealed class NavigationScreen(val title:String, val icon: Int, val route:String){

    // bottom navigation screens
    data object Home:NavigationScreen(
        title = "Home",
        icon =   R.drawable.baseline_home_24,
        route = "home"
    )
    data object Prediction:NavigationScreen(
        title = "Prediction",
        icon =   R.drawable.baseline_health_and_safety_24,
        route = "prediction"
    )

    data object MedicalTest:NavigationScreen(
        title = "Analysis",
        icon =   R.drawable.baseline_document_scanner_24,
        route = "analysis"
    )



    data object Medicine:NavigationScreen(
        title = "Medicine",
        icon =   R.drawable.baseline_medication_24,
        route = "medicine"
    )

    data object Calculator:NavigationScreen(
        title = "Calculator",
        icon =   R.drawable.baseline_calculate_24,
        route = "calculator"
    )
}