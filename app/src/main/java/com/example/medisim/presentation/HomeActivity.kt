package com.example.medisim.presentation

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.example.medisim.presentation.authScreens.forgotPassword.ForgotPasswordViewModel
import com.example.medisim.presentation.authScreens.login.LoginScreenViewModel
import com.example.medisim.presentation.authScreens.signUp.SignUpScreenViewModel
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.home.HomeViewModel
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.medicine.MedicineScreenViewModel
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.mediclaTest.MedicalTestScreenViewModel
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.disease.PredictionViewModel
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.skinDisease.SkinDiseaseScreenViewModel
import com.example.medisim.presentation.homeScreens.topNavigationScreens.chatAI.ChatAIViewModel
import com.example.medisim.presentation.homeScreens.topNavigationScreens.profile.ProfileViewModel
import com.example.medisim.presentation.navigation.AppNavigation
import com.example.medisim.ui.theme.MediSimTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private val profileViewModel by viewModels<ProfileViewModel>()
    private val homeViewModel by viewModels<HomeViewModel>()
    private val loginViewModel by viewModels<LoginScreenViewModel>()
    private val signUpScreenViewModel by viewModels<SignUpScreenViewModel>()
    private val forgotPasswordViewModel by viewModels<ForgotPasswordViewModel>()
    private val chatAIViewModel by viewModels<ChatAIViewModel>()
    private val medicineViewModel by viewModels<MedicineScreenViewModel>()
    private val predictionViewModel by viewModels<PredictionViewModel>()
    private val skinDiseaseViewModel by viewModels<SkinDiseaseScreenViewModel>()
    private val medicalTestViewModel by viewModels<MedicalTestScreenViewModel>()

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Now can access resources using the context
        val resources = this.resources
        // make app language like language in SharedPreferences
        profileViewModel.onCreateChangeLanguage(
            resources = resources,
            systemLanguage = getCurrentLanguage()
        )

        setContent {
            // this state manage profile screen that contain Dark mode and language
            // and need the state here to change app directly on change any of them
            val state = profileViewModel.state.value
            MediSimTheme (darkTheme = state.isDark){

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Get the layout direction based on view model state to make change directly
                    val layoutDirection = if (state.isRtlDirection) LayoutDirection.Rtl else LayoutDirection.Ltr

                    // Set layout direction, all app screens inside CompositionLocalProvider
                    // to change it if language changed
                    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                        val currentUserState = profileViewModel.getLoginState()
                        if(currentUserState){
                            homeViewModel.getAllPosts()
                        }
                        // UI code here, call app navigation to start application
                        AppNavigation(
                            currentUserState = currentUserState,
                            homeViewModel = homeViewModel,
                            loginViewModel = loginViewModel,
                            signUpScreenViewModel = signUpScreenViewModel,
                            forgotPasswordViewModel = forgotPasswordViewModel,
                            chatAIViewModel = chatAIViewModel,
                            profileViewModel = profileViewModel,
                            medicineViewModel = medicineViewModel,
                            predictionViewModel = predictionViewModel,
                            skinDiseaseViewModel = skinDiseaseViewModel,
                            medicalTestViewModel = medicalTestViewModel
                        )
                    }


                }
            }
        }
    }


    private fun getCurrentLanguage(): String {
        val configuration = Resources.getSystem().configuration
        return configuration.locales[0].language
    }
}



