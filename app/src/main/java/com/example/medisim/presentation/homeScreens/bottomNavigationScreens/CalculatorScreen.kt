package com.example.medisim.presentation.homeScreens.bottomNavigationScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medisim.R
import com.example.medisim.presentation.components.ImageButtonWithText


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculatorScreen() {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){

        ImageButtonWithText(
            image = R.drawable.body,
            text = stringResource(R.string.body_fat_percentage)
        ){}
        ImageButtonWithText(
            image = R.drawable.blood_volume,
            text = stringResource(R.string.estimated_blood_volume)
        ){}
        ImageButtonWithText(
            image = R.drawable.creatinine,
            text = stringResource(R.string.creatinine_clearance)
        ){}
    }
}