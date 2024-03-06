package com.example.medisim.presentation.homeScreens.topNavigationScreens.profile

import android.content.res.Resources
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
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(private val pref:SharedPreferences): ViewModel() {

    private var _state by mutableStateOf(
        ProfileState()
    )

    val state: State<ProfileState>
        get() = derivedStateOf { _state }

    init {
        val codeIsArabic =  pref.getSharedPreferences("language","") == "ar"
        _state = _state.copy(
            isDark = pref.getBooleanSharedPreferences("mode", true),
            isArabic  =  codeIsArabic ,
            isRtlDirection = codeIsArabic
        )
    }
    fun onCreateChangeLanguage(
        resources: Resources,
        systemLanguage:String
    ){
        // make languageCode equal to language that saved in
        var languageCode = pref.getSharedPreferences("language","")

        // check if this first time to open application or not
        // if its first time, make languageCode equal to systemLanguage
        if (pref.getBooleanSharedPreferences("firstTime",true)){
            // make languageCode is systemLanguage because it first time to open app.
            languageCode = systemLanguage
            pref.setSharedPreferences("language",systemLanguage)
            _state = _state.copy(
                isArabic  =  systemLanguage == "ar" ,
                isRtlDirection = systemLanguage == "ar"
            )
            // make firstTime false in Shared Preferences
            pref.setBooleanSharedPreferences("firstTime",false)

        }
        // set application language for the right language
        setLocale(languageCode,resources)
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


    fun onShowBottomSheet(){
        _state = _state.copy(
            isBottomSheetShow = true
        )
    }
    fun onDismissRequest(){
        _state = _state.copy(
            isBottomSheetShow = false
        )
    }

    fun onSelectLanguage(languageCode:String, resources: Resources){
        setLocale(languageCode,resources)
        pref.setSharedPreferences("language",languageCode)
        _state = _state.copy(
            isArabic = languageCode == "ar",
            isRtlDirection = languageCode == "ar"
        )
    }

    private fun setLocale(languageCode: String, resources: Resources){
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        config.setLayoutDirection(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}