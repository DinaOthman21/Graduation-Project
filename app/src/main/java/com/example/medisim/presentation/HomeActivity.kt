package com.example.medisim.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.medisim.presentation.authScreens.forgotPassword.ForgotPasswordViewModel
import com.example.medisim.presentation.authScreens.login.LoginScreenViewModel
import com.example.medisim.presentation.authScreens.signUp.SignUpScreenViewModel
import com.example.medisim.presentation.homeScreens.topNavigationScreens.chatAI.ChatAIViewModel
import com.example.medisim.presentation.homeScreens.topNavigationScreens.profile.ProfileViewModel
import com.example.medisim.presentation.navigation.AppNavigation
import com.example.medisim.ui.theme.MediSimTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private val loginViewModel by viewModels<LoginScreenViewModel>()
    private val signUpScreenViewModel by viewModels<SignUpScreenViewModel>()
    private val forgotPasswordViewModel by viewModels<ForgotPasswordViewModel>()
    private val chatAIViewModel by viewModels<ChatAIViewModel>()
    private val profileViewModel by viewModels<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            val state = profileViewModel.state.value
            MediSimTheme (darkTheme = state.isDark){

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(
                        loginViewModel = loginViewModel,
                        signUpScreenViewModel = signUpScreenViewModel,
                        forgotPasswordViewModel = forgotPasswordViewModel,
                        chatAIViewModel = chatAIViewModel,
                        profileViewModel = profileViewModel
                    )
                }
            }
        }
    }
}

