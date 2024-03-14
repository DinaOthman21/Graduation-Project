package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.disease

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.medisim.data.remote.dto.Symptom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

val list = listOf(
    Symptom(1,"Malaria","ملاريا"),
    Symptom(2,"Allergy","حساسية"),
    Symptom(3,"Hypothyroidism Hypothyroidism","قصور الغدة الدرقية"),
    Symptom(4,"Common cold","زُكام"),
    Symptom(5,"Hypoglycemia","نقص سكر الدم"),
    Symptom(6,"Diabetes","السكري")
)
class PredictionViewModel : ViewModel() {


    private var _state by mutableStateOf(PredictionScreenState())
    val state: State<PredictionScreenState>
        get() = derivedStateOf { _state }



    private val _listOfSymptoms  = MutableStateFlow(list)
    val listOfSymptoms : StateFlow<List<Symptom>> = _listOfSymptoms



    fun onSymptomNameChange(newSymptomName:String){
        _state = if (newSymptomName!= ""){
            _state.copy(
                editTextSymptom = newSymptomName,
                dropDownState = true
            )
        }else{
            _state.copy(
                editTextSymptom = newSymptomName,
                dropDownState = false
            )
        }


        // filter list based on the symptom that user type it in edit text.
        // -->
        // todo
    }

    fun onDropDownDismiss(){
        _state = _state.copy(
            dropDownState = _state.dropDownState.not()
        )
    }

    fun onDeleteSymptom(){

    }

    fun filter(list:List<String>, filterQuery:String):List<String>{
        return list.filter { it.contains(filterQuery) }
    }

    fun onPredictClick(){

    }


}