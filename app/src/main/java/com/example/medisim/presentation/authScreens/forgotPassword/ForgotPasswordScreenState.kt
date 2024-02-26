package com.example.medisim.presentation.authScreens.forgotPassword

data class ForgotPasswordScreenState(

    // first screen to send otp on email
    val email:String = "",
    val isErrorEmail:Boolean = false,
    val emailErrorMessage:String = "",



    // second screen to enter otp

    val code:String = "",


    // last screen to update password
    val password:String = "",
    val isErrorPassword:Boolean = false ,
    val passwordErrorMessage:String = "",

    val confirmPassword:String = "",
    val isErrorConfirmPassword:Boolean = false ,
    val confirmPasswordErrorMessage:String = "",


    val showPassword: Boolean = false,
    val showConfirmPassword:Boolean = false

    )