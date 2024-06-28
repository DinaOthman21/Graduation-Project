package com.example.medisim.presentation.navigation

import com.example.medisim.R


sealed class NavigationScreen( val icon: Int, val route:String){

    // bottom navigation screens
    data object Home:NavigationScreen(
        icon =   R.drawable.baseline_home_24,
        route = "home"
    )
    data object Prediction:NavigationScreen(
        icon =   R.drawable.baseline_health_and_safety_24,
        route = "prediction"
    )



    data object Drug:NavigationScreen(
        icon =   R.drawable.baseline_medication_24,
        route = "drug"
    )

    data object Calculator:NavigationScreen(
        icon =   R.drawable.baseline_calculate_24,
        route = "calculator"
    )
}