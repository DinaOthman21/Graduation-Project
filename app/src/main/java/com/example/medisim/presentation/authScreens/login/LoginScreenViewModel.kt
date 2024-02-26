package com.example.medisim.presentation.authScreens.login

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.medisim.R
import com.example.medisim.presentation.navigation.Screens

class LoginScreenViewModel : ViewModel() {

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
            emailErrorMessage = ""
        )
    }

    // when user write in password editText update state
    fun onPasswordChange(password:String){
        _state = _state.copy(
            password = password,
            isErrorPassword = false,
            passwordErrorMessage = ""
        )

    }

    // when user need to make password is visible
    fun onIconButtonClick(){
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
    fun onLoginClick(navController: NavHostController,context: Context){
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

        if (_state.password.isNotEmpty() && _state.email.isNotEmpty() && isValidEmail(_state.email)){


//            // try to login.
//            viewModelScope.launch(Dispatchers.IO){
//               val loginResponse =  authApiRepository.login(
//                    LoginRequestBody(
//                    _state.phoneNumber,
//                    _state.password
//                ))
//
//                if (loginResponse.token.isNotEmpty()){
//                    tokenManager.saveToken(loginResponse.token)
//
//                    // go to home screen after save token in sharedPreferences
//                    withContext(Dispatchers.Main){
//                        navController.navigate(Screens.Home.route){
//                            popUpTo(Screens.Login.route) {
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//
//            }
//
//
//
            // save token as example to save user if user make Remember me true
//            tokenManager.saveToken("token")
            navController.navigate(Screens.Home.route){
                popUpTo(Screens.Login.route) {
                    inclusive = true
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



}