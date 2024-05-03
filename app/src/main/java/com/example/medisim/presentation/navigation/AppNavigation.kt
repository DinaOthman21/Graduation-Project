package com.example.medisim.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.medisim.data.Constants
import com.example.medisim.data.remote.dto.main.Post
import com.example.medisim.presentation.authScreens.RegistrationSuccessfullyScreen
import com.example.medisim.presentation.authScreens.forgotPassword.ForgotPassword
import com.example.medisim.presentation.authScreens.forgotPassword.ForgotPasswordNewPassword
import com.example.medisim.presentation.authScreens.forgotPassword.ForgotPasswordOto
import com.example.medisim.presentation.authScreens.forgotPassword.ForgotPasswordViewModel
import com.example.medisim.presentation.authScreens.login.LoginScreen
import com.example.medisim.presentation.authScreens.login.LoginScreenViewModel
import com.example.medisim.presentation.authScreens.signUp.SignUpScreen
import com.example.medisim.presentation.authScreens.signUp.SignUpScreenViewModel
import com.example.medisim.presentation.authScreens.signUp.SignUpUserChronicScreen
import com.example.medisim.presentation.authScreens.signUp.SignUpUserInfoScreen
import com.example.medisim.presentation.homeScreens.MainScreen
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.calculator.MainCalculatorScreen
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.home.HomeScreen
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.home.HomeViewModel
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.home.PostDetails
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.mediclaTest.MedicalTestScreen
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.medicine.MedicineScreen
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.medicine.MedicineScreenViewModel
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.mediclaTest.MedicalTestScreenViewModel
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.PredictionScreen
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.disease.PredictionViewModel
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.skinDisease.SkinDiseaseScreenViewModel
import com.example.medisim.presentation.homeScreens.topNavigationScreens.chatAI.ChatAIViewModel
import com.example.medisim.presentation.homeScreens.topNavigationScreens.chatAI.ChatScreen
import com.example.medisim.presentation.homeScreens.topNavigationScreens.profile.ProfileViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun ChangeStatusBarColor(isBackgroundColor:Boolean) {
    val systemUiController = rememberSystemUiController()
    var statusBarColor = MaterialTheme.colorScheme.background
    if (isBackgroundColor){
         statusBarColor = MaterialTheme.colorScheme.tertiary // Set desired status bar color
    }
    SideEffect {
        // Update the status bar color when the screen is first composed
        systemUiController.setStatusBarColor(statusBarColor)
    }
}


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun AppNavigation(
    currentUserState:Boolean,
    homeViewModel:HomeViewModel,
    loginViewModel: LoginScreenViewModel,
    signUpScreenViewModel: SignUpScreenViewModel,
    forgotPasswordViewModel: ForgotPasswordViewModel,
    chatAIViewModel: ChatAIViewModel,
    profileViewModel: ProfileViewModel,
    medicineViewModel: MedicineScreenViewModel,
    predictionViewModel: PredictionViewModel,
    skinDiseaseViewModel: SkinDiseaseScreenViewModel,
    medicalTestViewModel: MedicalTestScreenViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = if (currentUserState) Screens.Home.route else Screens.Login.route ){
        composable(route = Screens.Login.route){
            // login screen
            LoginScreen(navController = navController,loginViewModel = loginViewModel)
        }
        composable(route = Screens.ForgotPassword.route){
            // ForgotPassword screen
            ForgotPassword(navController = navController,forgotPasswordViewModel = forgotPasswordViewModel)
        }
        composable(route = Screens.ForgotPasswordOTP.route){
            // Forgot Password OTP screen
            ForgotPasswordOto(navController = navController,forgotPasswordViewModel = forgotPasswordViewModel)
        }
        composable(route = Screens.ForgotPasswordNewPass.route){
            // Forgot Password NewPassword screen
            ForgotPasswordNewPassword(navController = navController, forgotPasswordViewModel = forgotPasswordViewModel)
        }
        composable(route = Screens.SignUp.route){
            // signUp screen
            SignUpScreen(navController = navController,signUpScreenViewModel = signUpScreenViewModel)
        }
        composable(route = Screens.UserInfo.route){
            // User Information screen
            SignUpUserInfoScreen(navController = navController,signUpScreenViewModel = signUpScreenViewModel)
        }
        composable(route = Screens.UserChronic.route){
            // User Chronic disease screen
            SignUpUserChronicScreen(navController = navController,signUpScreenViewModel=signUpScreenViewModel)
        }
        composable(route = Screens.RegistrationSuccessfully.route){
            // Registration Successfully screen
            RegistrationSuccessfullyScreen(navController = navController)
        }
        composable(route = Screens.Home.route){
            // Home screen
            ChangeStatusBarColor(isBackgroundColor = true)
            MainScreen(
                navController,
                homeViewModel,
                profileViewModel,
                predictionViewModel,
                medicineViewModel,
                skinDiseaseViewModel,
                medicalTestViewModel
            )
        }
        composable(
            route = Screens.PostDetails.route,
            ){
            // Post Details screen
            val post: Post? = navController.previousBackStackEntry?.savedStateHandle?.get("post")
            post?.let {
                PostDetails(post = it,navController = navController)
                ChangeStatusBarColor(isBackgroundColor = false)
            }


        }
        composable(route = Screens.ChatAI.route){
            // Post Chat AI screen
            ChatScreen(navController = navController,chatAIViewModel =chatAIViewModel)
            ChangeStatusBarColor(isBackgroundColor = false)

        }

    }
}


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun BottomNavigation(
    bottomNavController: NavHostController,
    appNavController: NavHostController,
    homeViewModel:HomeViewModel,
    medicineViewModel: MedicineScreenViewModel,
    predictionViewModel: PredictionViewModel,
    skinDiseaseViewModel: SkinDiseaseScreenViewModel,
    medicalTestViewModel:MedicalTestScreenViewModel

    ) {
    NavHost(navController = bottomNavController, startDestination = NavigationScreen.Home.route ){
        composable(route = NavigationScreen.Home.route){
            HomeScreen(appNavController,homeViewModel)
            ChangeStatusBarColor(isBackgroundColor = true)

        }
        composable(route = NavigationScreen.Prediction.route){
            PredictionScreen(
                predictionViewModel = predictionViewModel,
                skinDiseaseViewModel = skinDiseaseViewModel
            )
            ChangeStatusBarColor(isBackgroundColor = false)

        }
        composable(route = NavigationScreen.MedicalTest.route){
            MedicalTestScreen(medicalTestViewModel = medicalTestViewModel)
            ChangeStatusBarColor(isBackgroundColor = false)

        }
        composable(route = NavigationScreen.Drug.route){
            MedicineScreen(medicineViewModel = medicineViewModel)
            ChangeStatusBarColor(isBackgroundColor = false)

        }
        composable(route = NavigationScreen.Calculator.route){
            MainCalculatorScreen()
            ChangeStatusBarColor(isBackgroundColor = false)

        }

    }

}


