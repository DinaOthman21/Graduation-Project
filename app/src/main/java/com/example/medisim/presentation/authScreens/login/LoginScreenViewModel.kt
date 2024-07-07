package com.example.medisim.presentation.authScreens.login

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.medisim.R
import com.example.medisim.data.Constants
import com.example.medisim.data.remote.dto.auth.LoginBody
import com.example.medisim.domain.SharedPreferences
import com.example.medisim.domain.repository.ApiServicesRepository
import com.example.medisim.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val repo : ApiServicesRepository,
    private val pref: SharedPreferences
): ViewModel() {

    private var _state by mutableStateOf(
        LoginScreenState()
    )

    val state: State<LoginScreenState>
        get() = derivedStateOf { _state }


    // when user write in email editText update state
    fun onEmailChange(email:String){
        _state = _state.copy(
            email = email,
            isErrorEmail  = false,
            emailErrorMessage = "",
            errorMessage = ""
        )
    }

    // when user write in password editText update state
    fun onPasswordChange(password:String){
        _state = _state.copy(
            password = password,
            isErrorPassword = false,
            passwordErrorMessage = "",
            errorMessage = ""
        )

    }

    // when user need to make password is visible
    fun onIconShowPassword(){
        val newShowPassword = _state.showPassword.not()
        _state = _state.copy(
            showPassword = newShowPassword
        )
    }

    fun onRememberMeClick(){
        val newRememberMe = _state.rememberMe.not()
        _state = _state.copy(
            rememberMe = newRememberMe
        )
    }


    // user click on button to login
    // make check in data and then call login endpoint
    // to get token 
    fun onLoginClick(navController: NavHostController, context: Context, allPosts: () -> Unit){
        if (_state.email.isEmpty()){
            _state=_state.copy(
                isErrorEmail = true,
                emailErrorMessage = context.getString(R.string.please_enter_your_email)
            )

        }
        if (isValidEmail(_state.email).not()){
            _state=_state.copy(
                isErrorEmail = true,
                emailErrorMessage = context.getString(R.string.please_enter_valid_email)
            )

        }
        if (_state.password.isEmpty()){
            _state=_state.copy(
                isErrorPassword = true,
                passwordErrorMessage = context.getString(R.string.please_enter_your_password)
            )

        }
        if (!isValidPassword(_state.password)){
            _state=_state.copy(
                isErrorPassword = true,
                passwordErrorMessage = context.getString(R.string.please_enter_valid_password_8_char)
            )

        }

        if (_state.password.isNotEmpty() && _state.email.isNotEmpty() && isValidEmail(_state.email) && isValidPassword(_state.password)){

            viewModelScope.launch(Dispatchers.IO){
                Log.d("Tag",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> login vm 1")
                val loginResponse = repo.login(LoginBody(_state.email,_state.password))
                Log.d("Tag",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> login vm 2")

                if (loginResponse.token!=""){
                    Log.d("Tag",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> login vm 3")

                    pref.setSharedPreferences(Constants.TOKEN,loginResponse.token)
                    pref.setSharedPreferences(Constants.USER_NAME,loginResponse.userName)
                    pref.setSharedPreferences(Constants.EMAIL,_state.email)
                    // set token not first time that can not be null again.
                    if (pref.getBooleanSharedPreferences(Constants.FIRST_TIME_TOKEN,true)){
                        pref.setBooleanSharedPreferences(Constants.FIRST_TIME_TOKEN,false)
                    }

                    if (_state.rememberMe){
                        pref.setSharedPreferences(Constants.REMEMBER_ME,loginResponse.token)
                    }
                    _state = _state.copy(
                        errorMessage = ""
                    )
                    // go to home screen after save token in sharedPreferences
                    withContext(Dispatchers.Main){
                        // load posts home screen
                        allPosts()

                        navController.navigate(Screens.Home.route){
                            popUpTo(Screens.Login.route) {
                                inclusive = true
                            }
                        }
                    }
                }else{
                    _state = _state.copy(
                        errorMessage = "make sure email and password true*"
                    )
                }
            }

        }

    }




    private fun isValidEmail(email: String): Boolean {
        // Regular expression pattern to validate the email format
        val pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        // Check if the provided email matches the pattern
        return email.matches(Regex(pattern))
    }



    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }


}