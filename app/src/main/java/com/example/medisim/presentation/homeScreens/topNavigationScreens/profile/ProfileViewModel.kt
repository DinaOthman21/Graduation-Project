package com.example.medisim.presentation.homeScreens.topNavigationScreens.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.medisim.domain.SharedPreferences
import com.example.medisim.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(private val pref:SharedPreferences): ViewModel() {
    private var _state by mutableStateOf(
        ProfileState()
    )

    val state: State<ProfileState>
        get() = derivedStateOf { _state }

    init {
        _state = _state.copy(
            isDark = pref.getBooleanSharedPreferences("mode", true)
        )
    }

    fun onChangeMode() {
        pref.setBooleanSharedPreferences("mode", _state.isDark.not())
        _state = _state.copy(
            isDark = _state.isDark.not()
        )

    }


    fun onLogoutClick(navController: NavHostController){
        navController.navigate(Screens.Login.route){
            popUpTo(Screens.Home.route) {
                inclusive = true
            }
        }
    }
}