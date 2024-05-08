package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.disease

import com.example.medisim.data.remote.dto.main.PredictionDiseaseResponse

data class PredictionScreenState(
    val editTextSymptom:String = "",
    val dropDownState:Boolean = false,
    val predictionDiseaseResponse: PredictionDiseaseResponse? = null

)
