package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.skinDisease


import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class SkinDiseaseScreenViewModel : ViewModel() {

    private var _state by mutableStateOf<Bitmap?>(null)
    val state: State<Bitmap?>
        get() = derivedStateOf { _state }


    fun onSelectImage(bitmap:Bitmap?){
        _state = bitmap
    }


}