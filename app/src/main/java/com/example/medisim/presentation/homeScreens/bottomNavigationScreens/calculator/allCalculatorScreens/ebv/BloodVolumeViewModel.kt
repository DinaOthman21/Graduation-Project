package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.calculator.allCalculatorScreens.ebv


import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.ViewModel
import com.example.medisim.R


class BloodVolumeViewModel : ViewModel() {

    private var _state by mutableStateOf(BloodVolumeScreenState())
    val state: State<BloodVolumeScreenState>
        get() = derivedStateOf { _state }


    fun onWeightSelected(newWeight:Int){
        _state = _state.copy(
            weight = newWeight
        )
    }

    fun onAgeSelected(age:Int){
        _state = _state.copy(
            age = age
        )
    }
    fun onGenderSelected(isMale:Boolean){
        _state = _state.copy(
            isMale = isMale
        )
    }


    fun onDialogClosed(){
        _state = _state.copy(
            result = buildAnnotatedString { append("") },
            dialogState = false
        )
    }

    fun onCalcBloodVolume(context: Context){
        _state = _state.copy(
            result = buildAnnotatedString(context,calculateBloodVolume()),
            dialogState = true
        )
    }

    private fun calculateBloodVolume():Int{
        return if (_state.age <1){
            _state.weight * 85
        }else if(_state.age in 1..3){
            _state.weight * 90
        }else if (_state.age in 4..14){
            _state.weight * 80
        }else{
            if(_state.isMale){
                _state.weight * 70
            }else{
                _state.weight * 65
            }
        }
    }


    private fun buildAnnotatedString(context: Context,result:Int): AnnotatedString {
        return  buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.White)) {
                append(context.getString(R.string.estimated_blood_volume))
            }
            withStyle(style = SpanStyle(color =  Color(0xFF05C6F5))) {
                append("  $result \n")
            }


        }
    }
}