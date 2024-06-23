package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.calculator.allCalculatorScreens.calories


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


class CaloriesViewModel : ViewModel() {

    private var _state by mutableStateOf(CaloriesScreenState())
    val state: State<CaloriesScreenState>
        get() = derivedStateOf { _state }


    fun onWeightSelected(newWeight:Int){
        _state = _state.copy(
            weight = newWeight
        )
    }

    fun onSelectLightWork(){
        _state = _state.copy(
            workType = WorkType.Light
        )
    }


    fun onSelectModerateWork(){
        _state = _state.copy(
            workType = WorkType.Moderate
        )
    }

    fun onSelectHeavyWork(){
        _state = _state.copy(
            workType = WorkType.Heavy
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


    fun onCalcCalories(context: Context){

        if (_state.workType!=null){
            val calories = calculateBEN() * calculatePAL()
            val result = buildResultAnnotatedString(context,calories)
            _state = _state.copy(
                result = result
            )
        }

    }

    private fun buildResultAnnotatedString(context:Context,result:Double):AnnotatedString{
       return buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.White)) {
                append(context.getString(R.string.maintain_weight))
            }
            withStyle(style = SpanStyle(color =  Color(0xFF05C6F5))) {
                append("$result ")
            }
            withStyle(style = SpanStyle(color = Color.White)) {
                append(context.getString(R.string.calories_day_100))
            }

           withStyle(style = SpanStyle(color = Color.White)) {
               append(context.getString(R.string.mild_weight_loss))
           }
           withStyle(style = SpanStyle(color =  Color(0xFF05C6F5))) {
               append("${(90/100.0) * result} ")
           }
           withStyle(style = SpanStyle(color = Color.White)) {
               append(context.getString(R.string.calories_day_90))
           }

           withStyle(style = SpanStyle(color = Color.White)) {
               append(context.getString(R.string.weight_loss))
           }
           withStyle(style = SpanStyle(color =  Color(0xFF05C6F5))) {
               append("${(79/100.0) * result} ")
           }
           withStyle(style = SpanStyle(color = Color.White)) {
               append(context.getString(R.string.calories_day_79))
           }

           withStyle(style = SpanStyle(color = Color.White)) {
               append(context.getString(R.string.extreme_weight_loss))
           }
           withStyle(style = SpanStyle(color =  Color(0xFF05C6F5))) {
               append("${(59/100.0) * result} ")
           }
           withStyle(style = SpanStyle(color = Color.White)) {
               append(context.getString(R.string.calories_day_59))
           }




       }
    }



    private fun calculateBEN():Double{
        return if (_state.isMale){
            _state.weight * 24.0
        }else{
            _state.weight * 0.9 * 24.0
        }
    }

    private fun calculatePAL():Double{
        return when (_state.workType) {
            WorkType.Light -> {
                1.7
            }
            WorkType.Moderate -> {
                2.5
            }
            else -> {
                5.0
            }
        }
    }

}