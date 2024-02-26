package com.example.medisim.presentation.authScreens.login

data class LoginScreenState(
    val email:String = "",
    val isErrorEmail:Boolean = false,
    val emailErrorMessage:String = "",

    val password:String = "",
    val isErrorPassword:Boolean = false ,
    val passwordErrorMessage:String = "",

    val showPassword:Boolean = false,
)