package com.example.medisim.presentation.homeScreens.topNavigationScreens.profile

import android.content.res.Resources
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.medisim.data.Constants
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
        val codeIsArabic =  pref.getSharedPreferences(Constants.LANGUAGE,"") == Constants.LANGUAGE_AR_CODE
       _state = _state.copy(
            isDark = pref.getBooleanSharedPreferences(Constants.MODE, true),
            isArabic  =  codeIsArabic ,
            isRtlDirection = codeIsArabic,
        )
    }

    fun getUserInfo(){
        val userName = pref.getSharedPreferences(Constants.USER_NAME,"")
        val email = pref.getSharedPreferences(Constants.EMAIL,"")
        _state = _state.copy(
            userName = userName,
            email = email
        )
    }
    fun onCreateChangeLanguage(
        resources: Resources,
        systemLanguage:String
    ){
        // make languageCode equal to language that saved in
        var languageCode = pref.getSharedPreferences(Constants.LANGUAGE,"")

        // check if this first time to open application or not
        // if its first time, make languageCode equal to systemLanguage
        if (pref.getBooleanSharedPreferences(Constants.FIRST_TIME,true)){
            // make languageCode is systemLanguage because it first time to open app.
            languageCode = systemLanguage
            pref.setSharedPreferences(Constants.LANGUAGE,systemLanguage)
            _state = _state.copy(
                isArabic  =  systemLanguage == Constants.LANGUAGE_AR_CODE ,
                isRtlDirection = systemLanguage == Constants.LANGUAGE_AR_CODE
            )
            // make firstTime false in Shared Preferences
            pref.setBooleanSharedPreferences(Constants.FIRST_TIME,false)

        }
        // set application language for the right language
        setLocale(languageCode,resources)
    }

    fun onChangeMode() {
        pref.setBooleanSharedPreferences(Constants.MODE, _state.isDark.not())
        _state = _state.copy(
            isDark = _state.isDark.not()
        )

    }


    fun onLogoutClick(navController: NavHostController){
        pref.setSharedPreferences(Constants.TOKEN,Constants.NO_TOKEN)
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
        pref.setSharedPreferences(Constants.LANGUAGE,languageCode)
        _state = _state.copy(
            isArabic = languageCode == Constants.LANGUAGE_AR_CODE,
            isRtlDirection = languageCode == Constants.LANGUAGE_AR_CODE
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


    fun getLoginState():Boolean{
        // if its first time, false to go into login page
        if (pref.getBooleanSharedPreferences(Constants.FIRST_TIME_TOKEN,true)){
            Log.d("Tag",">>>>>>>>>>>>>>>>>>> if its first time token <<<<<<<<<<<<<<<<<<<<<<<<<<<<")
            return false
        }

        val tok = pref.getSharedPreferences(Constants.TOKEN,"")
        val rememberMe = pref.getSharedPreferences(Constants.REMEMBER_ME,"")

        // if user logout from app, go into login page
        if (tok == Constants.NO_TOKEN){
            Log.d("Tag",">>>>>>>>>>>>>>>>>>> NO_TOKEN <<<<<<<<<<<<<<<<<<<<<<<<<<<<")
            return false
        }else{
            // false, if user not select remember me when login
            // true,  if user select remember me when login or he was register for first time
            Log.d("Tag",">>>>>>>>>>>>>>>>>>> Token ${tok == rememberMe} <<<<<<<<<<<<<<<<<<<<<<<<<<<<")
            return tok == rememberMe
        }
        // this case most be not happened
        Log.d("Tag",">>>>>>>>>>>>>>>>>>> false in end <<<<<<<<<<<<<<<<<<<<<<<<<<<<")
        return false
    }

}