package com.example.medisim.presentation.navigation

import androidx.compose.runtime.Composable
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
            MainScreen(navController)
        }
        composable(route = Screens.PostDetails.route){
            // Post Details screen
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
        }
        composable(route = NavigationScreen.Prediction.route){
            PredictionScreen()
        }
        composable(route = NavigationScreen.MedicalTest.route){
            MedicalTestScreen()
        }
        composable(route = NavigationScreen.Drug.route){
            MedicineScreen()
        }
        composable(route = NavigationScreen.Calculator.route){
            CalculatorScreen()
        }

    }

}