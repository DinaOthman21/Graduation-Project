package com.example.medisim.presentation.authScreens.forgotPassword

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.medisim.R
import com.example.medisim.data.remote.dto.auth.ChangePasswordRequestBody
import com.example.medisim.data.remote.dto.auth.ForgetPasswordRequestBody
import com.example.medisim.data.remote.dto.auth.VerifyOtpRequestBody
import com.example.medisim.domain.repository.ApiServicesRepository
import com.example.medisim.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val repo : ApiServicesRepository,) : ViewModel() {

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


    // TODO: send otp request to change password
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
            viewModelScope.launch(Dispatchers.IO){
                repo.forgetPassword(ForgetPasswordRequestBody(_state.email))
            }
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

    // TODO: verify otp
    fun onSendOtpToServer(navController: NavHostController, context:Context){
        if (_state.otpNumber.isEmpty()){
            _state=_state.copy(
                isErrorEmail = true,
                emailErrorMessage = context.getString(R.string.please_enter_otp_number)
            )

        }
        if (_state.otpNumber.isNotEmpty() && _state.otpNumber.length==6){
            // first send to back end to send otp number
            viewModelScope.launch(Dispatchers.IO){
                val check = repo.verifyOtp(VerifyOtpRequestBody(_state.email,_state.otpNumber))
                if (check){
                    withContext(Dispatchers.Main){
                        // go to new password screen if server return valid otp
                        navController.navigate(Screens.ForgotPasswordNewPass.route)
                    }
                }else{
                    _state=_state.copy(
                        isErrorEmail = true,
                        emailErrorMessage = context.getString(R.string.otp_number_not_correct)
                    )
                }
            }

        }



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


    // TODO: change password
    fun onSaveClick(navController: NavHostController,context: Context){
        if (_state.password.isEmpty()){
            _state=_state.copy(
                isErrorPassword = true,
                passwordErrorMessage = context.getString(R.string.please_enter_your_password)
            )

        }

        if (_state.confirmPassword.isEmpty()){
            _state=_state.copy(
                isErrorConfirmPassword = true,
                confirmPasswordErrorMessage = context.getString(R.string.please_enter_confirm_password)
            )

        }

        if (_state.confirmPassword != _state.password ){
            _state=_state.copy(
                isErrorConfirmPassword = true,
                confirmPasswordErrorMessage = context.getString(R.string.confirm_password_not_match_password)
            )

        }


        // cal api to change new password
        if (_state.confirmPassword.isNotEmpty() &&
            _state.password.isNotEmpty() &&
            _state.confirmPassword == _state.password ){

            viewModelScope.launch(Dispatchers.IO){
                val check = repo.changePassword(ChangePasswordRequestBody(_state.email,_state.password,_state.confirmPassword))
                if (check){
                    withContext(Dispatchers.Main){
                        // second got to Login if all is good
                        navController.navigate(Screens.Login.route){
                            popUpTo(Screens.ForgotPasswordNewPass.route) {
                                inclusive = true
                            }
                        }
                    }
                }else{
                    _state=_state.copy(
                        isErrorConfirmPassword = true,
                        confirmPasswordErrorMessage = context.getString(R.string.some_error_happen_please_make_sure_password_match_confirmpassword)

                    )
                }
            }

        }



    }


}