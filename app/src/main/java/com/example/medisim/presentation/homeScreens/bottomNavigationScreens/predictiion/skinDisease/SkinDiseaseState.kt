package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.skinDisease

import android.graphics.Bitmap
import com.example.medisim.data.remote.dto.main.SkinDiseaseResponse

data class SkinDiseaseState(
    val image: Bitmap? = null,
    val skinDiseaseResponse: SkinDiseaseResponse? = null,
    val recommendation :Pair<String,String>? = null,
    val dialogState:Boolean = false
)
