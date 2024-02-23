package com.example.medisim

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import com.example.medisim.presentation.HomeActivity
import com.example.medisim.presentation.components.SplashScreen
import com.example.medisim.ui.theme.MediSimTheme
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediSimTheme{
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


