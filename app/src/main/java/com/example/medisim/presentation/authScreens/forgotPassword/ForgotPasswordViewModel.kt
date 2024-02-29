package com.example.medisim.presentation.authScreens.forgotPassword

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

class ForgotPasswordViewModel : ViewModel() {
    private var _state by mutableStateOf(
        ForgotPasswordScreenState()
    )

    val state: State<ForgotPasswordScreenState>
        get() = derivedStateOf { _state }



    // when user write in email editText update state
    fun onEmailChange(email:String){
        _state = _state.copy(
            email = email,
            isErrorEmail  = false,
            emailErrorMessage = ""
        )
    }

    private fun isValidEmail(email: String): Boolean {
        // Regular expression pattern to validate the email format
        val pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        // Check if the provided email matches the pattern
        return email.matches(Regex(pattern))
    }


    fun onSendOtpToEmail(navController: NavHostController, context:Context){
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

        if (_state.email.isNotEmpty() && isValidEmail(_state.email)){
            // first send to back end to send otp number

            // second go to otp screen
            navController.navigate(Screens.ForgotPasswordOTP.route)
        }

        }

    /////////////////////////////////////////////////
    /////// second screen


    fun onOtpCodeChange(newOtp: String) {
        _state = _state.copy(
            otpNumber = newOtp,
            otpErrorMessage = ""
        )
    }

    fun onSendOtpToServer(navController: NavHostController){
     // go to new password screen if server return valid otp
        navController.navigate(Screens.ForgotPasswordNewPass.route)
    }



    /////////////////////////////////////////////////
    ////// last screen
    // when user write in password editText update state
    fun onPasswordChange(password:String){
        _state = _state.copy(
            password = password,
            isErrorPassword = false,
            passwordErrorMessage = ""
        )

    }


    // when user write in password editText update state
    fun onConfirmPasswordChange(password:String){
        _state = _state.copy(
            confirmPassword = password,
            isErrorConfirmPassword  = false,
            confirmPasswordErrorMessage = ""
        )

    }



    // when user need to make password is visible
    fun onIconShowPassword(){
        val newShowPassword = _state.showPassword.not()
        _state = _state.copy(
            showPassword = newShowPassword
        )
    }

    // when user need to make confirm password is visible
    fun onIconShowConfirmPassword(){
        val newShowConfirmPassword = _state.showConfirmPassword.not()
        _state = _state.copy(
            showConfirmPassword = newShowConfirmPassword
        )
    }

    fun onSaveClick(navController: NavHostController){
        // first validate password

        // second got to home if all is good
        navController.navigate(Screens.Home.route){
            popUpTo(Screens.ForgotPasswordNewPass.route) {
                inclusive = true
            }
        }
    }


}