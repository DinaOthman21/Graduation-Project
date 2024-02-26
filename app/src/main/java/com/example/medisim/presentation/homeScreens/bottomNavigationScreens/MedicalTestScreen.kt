package com.example.medisim.presentation.homeScreens.bottomNavigationScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.medisim.R
import com.example.medisim.presentation.components.ButtonClickOn
import com.example.medisim.presentation.components.TextLabel

@Composable
fun MedicalTestScreen() {
    Column(
        modifier = Modifier.padding(16.dp)
    ){
        TextLabel(
            text = stringResource(R.string.medical_tests),
            textFont = 22,
            textFontWight = FontWeight.Bold
        )
//        LottieAnimationShow(
//            animationResId = ,
//            size = ,
//            padding = ,
//            paddingBottom =
//        )
        Spacer(modifier = Modifier.weight(1f))
        ButtonClickOn(
            buttonText = stringResource(R.string.upload_test),
            modifier = Modifier.padding(bottom = 25.dp),
            paddingValue = 0
        ) {

        }

    }
}