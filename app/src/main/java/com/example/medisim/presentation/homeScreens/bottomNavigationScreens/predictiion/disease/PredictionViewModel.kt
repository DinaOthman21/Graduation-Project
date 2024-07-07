package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.disease

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medisim.data.remote.dto.main.Symptom
import com.example.medisim.domain.repository.ApiServicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PredictionViewModel @Inject constructor(private val repo:ApiServicesRepository): ViewModel() {


    private var _state by mutableStateOf(PredictionScreenState())
    val state: State<PredictionScreenState>
        get() = derivedStateOf { _state }



    private val _listOfSymptoms  = MutableStateFlow(emptyList<Symptom>())

    private val _listOfSelectedSymptoms  = MutableStateFlow(emptyList<Symptom>())
    val listOfSelectedSymptoms : StateFlow<List<Symptom>> = _listOfSelectedSymptoms

    private val _dropDownList  = MutableStateFlow(emptyList<Symptom>())
    val dropDownList : StateFlow<List<Symptom>> = _dropDownList


    init {
       getSymptomsList()
    }


    private fun getSymptomsList(){
        viewModelScope.launch (Dispatchers.IO){
            repo.getSymptoms().collect{
                _listOfSymptoms.value = it
            }
        }
    }

    private fun addToListOfSymptoms(symptom:Symptom){
        val currentList = _listOfSymptoms.value.toMutableList()
        currentList.add(symptom)
        _listOfSymptoms.value = currentList.toList()
    }
    private fun deleteFromListOfSymptoms(symptom:Symptom){
        val currentList = _listOfSymptoms.value.toMutableList()
        currentList.remove(symptom)
        _listOfSymptoms.value = currentList.toList()
    }
    private fun resetSelectedList(){
        for (symptom in _listOfSelectedSymptoms.value){
            addToListOfSymptoms(symptom)
            deleteFromSelectedList(symptom)
        }
    }

    fun addSymptomToSelectedList(symptom:Symptom){
        // add selected symptom to selected list
        val currentList = _listOfSelectedSymptoms.value.toMutableList()
        currentList.add(symptom)
        _listOfSelectedSymptoms.value = currentList.toList()
        // remove this symptom from base list to avoid reSelected
        deleteFromListOfSymptoms(symptom)

        // delete text user write it in EditText and close the dropDown List
        _state = _state.copy(
            editTextSymptom = "",
            dropDownState = false
        )
    }
    fun deleteFromSelectedList(symptom:Symptom){
        // delete symptom from selected list
        val currentList = _listOfSelectedSymptoms.value.toMutableList()
        currentList.remove(symptom)
        _listOfSelectedSymptoms.value = currentList.toList()

        // then add it to base list again
        addToListOfSymptoms(symptom)
    }


    fun onSymptomNameChange(newSymptomName:String,isArabic:Boolean){
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
        _dropDownList.value = filter(newSymptomName,isArabic).toMutableList()
    }



    private fun filter(filterQuery:String,isArabic: Boolean):List<Symptom>{
        val list = mutableListOf<Symptom>()
        _listOfSymptoms.value.forEach {
            if (isArabic){
               if ( it.arName.toLowerCase().contains(filterQuery.toLowerCase())){
                   list.add(it)
               }
            }else{
                if (it.enName.toLowerCase().contains(filterQuery.toLowerCase())){
                    list.add(it)
                }
            }
        }
        return list
    }

    fun onDialogDismiss(){
        _state = _state.copy(
            dialogState = false
        )
    }

    fun onPredictClick(){
        val list = mutableListOf<Int>()
        _listOfSelectedSymptoms.value.forEach {
            list.add(it.id)
        }
        viewModelScope.launch(Dispatchers.IO){
            val result = repo.predict(selectedSymptomIDs = list)
            _state = _state.copy(
                predictionDiseaseResponse = result,
                dialogState = true
            )
            // resetSelectedList()
        }


    }


}