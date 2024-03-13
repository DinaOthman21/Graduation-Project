package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.medicine

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.medisim.data.remote.dto.Medicine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class MedicineScreenViewModel : ViewModel() {
    private var _medicine by mutableStateOf<Medicine?>(null)
    val medicine: State<Medicine?>
        get() = derivedStateOf { _medicine }


    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery


    fun onSearchEditTextChange(newQuery:String){
        _searchQuery.value = newQuery
    }

    fun onIconSearchClick(){
        search(_searchQuery.value)
    }


    private fun search(newQuery:String){
        // call api to get medicine that user search for it


    }


}