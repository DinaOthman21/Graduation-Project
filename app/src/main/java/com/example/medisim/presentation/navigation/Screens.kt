package com.example.medisim.presentation.navigation


sealed class Screens(val route:String){

    data object Login : Screens(route = "login")
    data object ForgotPassword : Screens(route = "forgotPassword")
    data object ForgotPasswordOTP : Screens(route = "forgotPasswordOtp")
    data object ForgotPasswordNewPass : Screens(route = "forgotPasswordNewPass")
    data object SignUp : Screens(route = "signUp")
    data object UserInfo : Screens(route = "userInfo")
    data object UserChronic : Screens(route = "userChronic")
    data object RegistrationSuccessfully : Screens(route = "registrationSuccessfully")
    data object Home : Screens(route = "home")
    data object PostDetails : Screens(route = "postDetails")
    data object ChatAI : Screens(route = "chatAI")
    data object BmiCalculator : Screens(route = "bmiCalculator")

}