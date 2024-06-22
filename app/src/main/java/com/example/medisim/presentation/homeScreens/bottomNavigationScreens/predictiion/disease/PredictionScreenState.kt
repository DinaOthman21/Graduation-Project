package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.disease

import com.example.medisim.data.remote.dto.main.PredictionDisease

data class PredictionScreenState(
    val editTextSymptom:String = "",
    val dropDownState:Boolean = false,
    val predictionDiseaseResponse: List<PredictionDisease>? = null,
    val dialogState:Boolean = false

)
