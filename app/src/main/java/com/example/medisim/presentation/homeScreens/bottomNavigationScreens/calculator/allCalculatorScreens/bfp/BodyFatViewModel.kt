package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.calculator.allCalculatorScreens.bfp


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


class BodyFatViewModel : ViewModel() {

    private var _state by mutableStateOf(BodyFatScreenState())
    val state: State<BodyFatScreenState>
        get() = derivedStateOf { _state }


    fun onWeightSelected(newWeight:Int){
        _state = _state.copy(
            weight = newWeight
        )
    }

    fun onHeightSelected(newHeight:Int){
        _state = _state.copy(
            height = newHeight
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

    fun onCalcBFP(context: Context){
        val bmi = calculateBMI(_state.weight,_state.height)
        val bfp:Double
        // check user Male or Female
        if (_state.isMale){
            // check user adult or not
            bfp = if (_state.age>14){

                1.20 * bmi + 0.23 * _state.age - 16.2
            }else{
                1.51 * bmi + 0.70 * _state.age - 2.2
            }

        }else{
            // check user adult or not
            bfp = if (_state.age>14){
                1.20 * bmi + 0.23 * _state.age - 5.4
            }else{
                1.51 * bmi + 0.70 * _state.age - 1.4
            }
        }
        _state = _state.copy(
            result = buildResultString(context,bfp),
            dialogState = true
        )
    }

    private fun calculateBMI(weightKg: Int, heightCm: Int): Double {
        // Convert height from cm to meters
        val heightM = heightCm / 100.0
        return weightKg / (heightM * heightM)
    }

    private fun buildResultString(context: Context,result:Double):AnnotatedString{
        return buildAnnotatedString {

            withStyle(style = SpanStyle(color = Color.White)) {
                append(context.getString(R.string.body_fat))
            }
            withStyle(style = SpanStyle(color =  Color(0xFF05C6F5))) {
                append("$result% ")
            }
        }
    }

}
