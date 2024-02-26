package com.example.medisim.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.medisim.presentation.authScreens.login.LoginScreenViewModel
import com.example.medisim.presentation.navigation.AppNavigation
import com.example.medisim.ui.theme.MediSimTheme


class HomeActivity : ComponentActivity() {
    private val loginViewModel by viewModels<LoginScreenViewModel>()





    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent {
            MediSimTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(
                        loginViewModel = loginViewModel
                    )
                }
            }
        }
    }
}

