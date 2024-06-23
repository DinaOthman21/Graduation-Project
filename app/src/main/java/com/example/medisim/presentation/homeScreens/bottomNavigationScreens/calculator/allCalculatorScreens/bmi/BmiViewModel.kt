package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.calculator.allCalculatorScreens.bmi

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


class BmiViewModel : ViewModel() {


    private var _state by mutableStateOf(BmiScreenState())
    val state: State<BmiScreenState>
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

    fun onDialogClosed(){
        _state = _state.copy(
            result = buildAnnotatedString { append("") },
            dialogState = false
        )
    }

    fun onCalcBMI(context: Context){
        val annotatedString = calculateWeightCategory(
            context,
            calculateBMI(_state.weight,_state.height)
            )
        _state = _state.copy(
            result = annotatedString +  buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White)) {
                    append(context.getString(R.string.maintaining_a_healthy_weight_reduces_the_risk_of_diseases_associated_with_overweight_and_obesity))
                }
            },
            dialogState = true
        )

    }


    private fun calculateBMI(weightKg: Int, heightCm: Int): Double {
        // Convert height from cm to meters
        val heightM = heightCm / 100.0
        return weightKg / (heightM * heightM)
    }

    private fun calculateWeightCategory(context: Context,bmi:Double): AnnotatedString {
        return if (bmi < 18.5) {
            buildAnnotatedString(context,context.getString(R.string.less_than_18_5),
                context.getString(R.string.underweight))
        } else if (bmi >= 18.5 && bmi < 25) {
            buildAnnotatedString(context,context.getString(R.string.in_range_18_5_24_9),
                context.getString(R.string.normal))
        } else if (bmi >= 25 && bmi < 30) {
            buildAnnotatedString(context,context.getString(R.string.in_range_25_29_9),
                context.getString(R.string.overweight))
        } else {
            buildAnnotatedString(context,context.getString(R.string._30_or_greater),
                context.getString(R.string.obese))
        }

    }

    private fun buildAnnotatedString(context: Context,condition:String,category:String):AnnotatedString{
        return  buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.White)) {
                append(context.getString(R.string.a_bmi_of))
            }
            withStyle(style = SpanStyle(color =  Color(0xFF05C6F5))) {
                append("$condition ")
            }
            withStyle(style = SpanStyle(color = Color.White)) {
                append(context.getString(R.string.indicates_that_your_weight_is_in_the))
            }
            withStyle(style = SpanStyle(color =  Color(0xFF05C6F5))) {
                append("$category ")
            }
            withStyle(style = SpanStyle(color = Color.White)) {
                append(context.getString(R.string.category_for_a_person_of_your_height))
            }

        }
    }

}


