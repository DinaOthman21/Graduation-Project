package com.example.medisim.presentation.authScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medisim.R
import com.example.medisim.presentation.components.ButtonClickOn
import com.example.medisim.presentation.components.LottieAnimationShow
import com.example.medisim.presentation.components.TextLabel
import com.example.medisim.presentation.navigation.Screens


@Composable
fun RegistrationSuccessfullyScreen (navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimationShow(
            animationResId = R.raw.confirm_animation,
            size = 400,
            padding = 12,
            paddingBottom = 0
        )
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TextLabel(
                text = "Your Account Created",
                textFontWight = FontWeight.Bold,
                textFont = 26
            )
            TextLabel(
                text = "Successfully",
                textFontWight = FontWeight.Bold,
                textFont = 26
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        ButtonClickOn(
            buttonText = stringResource(R.string.start_now),
            paddingValue = 0) {
            navController.navigate(Screens.Home.route)
        }
    }

}