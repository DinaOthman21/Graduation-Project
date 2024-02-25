package com.example.medisim.presentation.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medisim.presentation.authScreens.RegistrationSuccessfullyScreen
import com.example.medisim.presentation.authScreens.forgotPassword.ForgotPassword
import com.example.medisim.presentation.authScreens.forgotPassword.ForgotPasswordNewPassword
import com.example.medisim.presentation.authScreens.forgotPassword.ForgotPasswordOto
import com.example.medisim.presentation.authScreens.login.LoginScreen
import com.example.medisim.presentation.authScreens.signUp.SignUpScreen
import com.example.medisim.presentation.authScreens.signUp.SignUpUserChronicScreen
import com.example.medisim.presentation.authScreens.signUp.SignUpUserInfoScreen
import com.example.medisim.presentation.homeScreens.MainScreen
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.CalculatorScreen
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.HomeScreen
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.MedicalTestScreen
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.MedicineScreen
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.PredictionScreen
import com.example.medisim.presentation.homeScreens.topNavigationScreens.chatAI.ChatScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun ChangeStatusBarColor(isHome:Boolean) {
    val systemUiController = rememberSystemUiController()
    var statusBarColor = MaterialTheme.colorScheme.background
    if (isHome){
         statusBarColor = MaterialTheme.colorScheme.tertiary // Set desired status bar color
    }
    SideEffect {
        // Update the status bar color when the screen is first composed
        systemUiController.setStatusBarColor(statusBarColor)
    }
}


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Login.route){
        composable(route = Screens.Login.route){
            // login screen
            LoginScreen(navController = navController)
        }
        composable(route = Screens.ForgotPassword.route){
            // ForgotPassword screen
            ForgotPassword(navController = navController)
        }
        composable(route = Screens.ForgotPasswordOTP.route){
            // Forgot Password OTP screen
            ForgotPasswordOto(navController = navController)
        }
        composable(route = Screens.ForgotPasswordNewPass.route){
            // Forgot Password NewPassword screen
            ForgotPasswordNewPassword(navController = navController)
        }
        composable(route = Screens.SignUp.route){
            // signUp screen
            SignUpScreen(navController = navController)
        }
        composable(route = Screens.UserInfo.route){
            // User Information screen
            SignUpUserInfoScreen(navController = navController)
        }
        composable(route = Screens.UserChronic.route){
            // User Chronic disease screen
            SignUpUserChronicScreen(navController = navController)
        }
        composable(route = Screens.RegistrationSuccessfully.route){
            // Registration Successfully screen
            RegistrationSuccessfullyScreen(navController = navController)
        }
        composable(route = Screens.Home.route){
            // Home screen
            ChangeStatusBarColor(isHome = true)
            MainScreen(navController)
        }
        composable(route = Screens.PostDetails.route){
            // Post Details screen

            ChangeStatusBarColor(isHome = false)

        }
        composable(route = Screens.ChatAI.route){
            // Post Chat AI screen
            ChatScreen()
            ChangeStatusBarColor(isHome = false)

        }

    }
}


@Composable
fun BottomNavigation(
    bottomNavController: NavHostController,
    appNavController: NavHostController,

    ) {
    NavHost(navController = bottomNavController, startDestination = NavigationScreen.Home.route ){
        composable(route = NavigationScreen.Home.route){
            HomeScreen(appNavController)
            ChangeStatusBarColor(isHome = true)

        }
        composable(route = NavigationScreen.Prediction.route){
            PredictionScreen()
            ChangeStatusBarColor(isHome = false)

        }
        composable(route = NavigationScreen.MedicalTest.route){
            MedicalTestScreen()
            ChangeStatusBarColor(isHome = false)

        }
        composable(route = NavigationScreen.Drug.route){
            MedicineScreen()
            ChangeStatusBarColor(isHome = false)

        }
        composable(route = NavigationScreen.Calculator.route){
            CalculatorScreen()
            ChangeStatusBarColor(isHome = false)

        }

    }

}