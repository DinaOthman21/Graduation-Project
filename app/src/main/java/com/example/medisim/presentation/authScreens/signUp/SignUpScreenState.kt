package com.example.medisim.presentation.authScreens.signUp

data class SignUpScreenState(
    // account data in first screen
    val userName:String = "",
    val isErrorUserName:Boolean = false,
    val userNameErrorMessage:String = "",

    val email:String = "",
    val isErrorEmail:Boolean = false,
    val emailErrorMessage:String = "",

    val password:String = "",
    val isErrorPassword:Boolean = false ,
    val passwordErrorMessage:String = "",

    val confirmPassword:String = "",
    val isErrorConfirmPassword:Boolean = false ,
    val confirmPasswordErrorMessage:String = "",

    val showPassword: Boolean = false,
    val showConfirmPassword:Boolean = false,


    // user information in second screen
    val height:String = "",
    val isErrorHeight:Boolean = false,
    val heightErrorMessage:String = "",

    val weight:String = "",
    val isErrorWeight:Boolean = false,
    val weightErrorMessage:String = "",

    val age:String = "",
    val isErrorAge:Boolean = false,
    val ageErrorMessage:String = "",



    val male:Boolean = false,
    val female:Boolean = false,


    // chronic disease for user (will be handel in next time).
)