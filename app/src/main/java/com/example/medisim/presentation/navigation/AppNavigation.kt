package com.example.medisim.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medisim.presentation.authScreens.login.LoginScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Login.route){
        composable(route = Screens.Login.route){
            // login screen
            LoginScreen()
        }
        composable(route = Screens.ForgotPassword.route){
            // ForgotPassword screen
        }
        composable(route = Screens.ForgotPasswordOTP.route){
            // Forgot Password OTP screen
        }
        composable(route = Screens.ForgotPasswordNewPass.route){
            // Forgot Password NewPassword screen
        }
        composable(route = Screens.SignUp.route){
            // signUp screen
        }
        composable(route = Screens.UserInfo.route){
            // User Information screen
        }
        composable(route = Screens.UserChronic.route){
            // User Chronic disease screen
        }
        composable(route = Screens.RegistrationSuccessfully.route){
            // Registration Successfully screen
        }
        composable(route = Screens.Home.route){
            // Home screen
        }
        composable(route = Screens.PostDetails.route){
            // Post Details screen
        }

    }
}