package com.example.medisim

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.medisim.presentation.HomeActivity
import com.example.medisim.presentation.components.SplashScreen
import com.example.medisim.presentation.homeScreens.topNavigationScreens.profile.ProfileViewModel
import com.example.medisim.ui.theme.MediSimTheme
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
// mahmoudadel5556@gmail.com
// 123456789


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val profileViewModel by viewModels<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state = profileViewModel.state.value
            MediSimTheme(darkTheme = state.isDark){
                // to hide // Status & Navigation bars
                val systemUiController: SystemUiController = rememberSystemUiController()
                systemUiController.isSystemBarsVisible = false // Status & Navigation bars

                // Splash Screen
                SplashScreen()
                LaunchedEffect(Unit) {
                    delay(2000)

                    // go to home screen
                    val intent = Intent(this@MainActivity, HomeActivity::class.java)

                    startActivity(intent)

                    // clear current activity from back stack.
                    finish()

                }
            }
        }
    }
}


