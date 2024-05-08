package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.medicine

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medisim.data.remote.dto.main.Medicine
import com.example.medisim.domain.repository.ApiServicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MedicineScreenViewModel @Inject constructor(private val repo:ApiServicesRepository): ViewModel() {
    private var _medicine by mutableStateOf<Medicine?>(null)
    val medicine: State<Medicine?>
        get() = derivedStateOf { _medicine }


    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery


    fun onSearchEditTextChange(newQuery:String){
        _searchQuery.value = newQuery
    }


    fun search(){
        // call api to get medicine that user search for it
        viewModelScope.launch (Dispatchers.IO){
            if (_searchQuery.value != ""){
                _medicine = repo.search(_searchQuery.value)
            }
        }


    }


}