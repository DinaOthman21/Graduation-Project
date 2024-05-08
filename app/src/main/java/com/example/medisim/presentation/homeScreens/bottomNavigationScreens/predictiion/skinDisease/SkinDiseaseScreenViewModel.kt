package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.skinDisease


import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medisim.data.remote.dto.main.SkinDiseaseBody
import com.example.medisim.domain.repository.ApiServicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SkinDiseaseScreenViewModel @Inject constructor(private val repo:ApiServicesRepository): ViewModel() {

    private var _state by mutableStateOf(SkinDiseaseState())
    val state: State<SkinDiseaseState>
        get() = derivedStateOf { _state }


    fun onSelectImage(bitmap:Bitmap?){
        _state = _state.copy(
            image = bitmap
        )
    }


    fun onDetectClick(){
        viewModelScope.launch(Dispatchers.IO){
            repo.skinDetect(SkinDiseaseBody(_state.image!!))
        }
    }


}