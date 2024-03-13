package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.example.medisim.R
import com.example.medisim.presentation.components.ButtonClickOn
import com.example.medisim.presentation.components.DropdownMenuExample
import com.example.medisim.presentation.components.LottieAnimationShow

@PreviewScreenSizes
@Composable
fun DiseasePredictionScreen() {
    Column(
        modifier = Modifier.padding(16.dp)
    ){

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            LottieAnimationShow(
                animationResId = R.raw.files_animation,
                size = 250,
                padding = 12,
                paddingBottom = 0
            )
        }
        DropdownMenuExample(
            selectedItem = "",
            expanded = true,
            items = list,
            onValueChange = {},
            onDropDownListDismissRequest = { /*TODO*/ }) {

        }
        Spacer(modifier = Modifier.weight(1f))
        ButtonClickOn(
            buttonText = stringResource(R.string.predict),
            modifier = Modifier.padding(bottom = 25.dp),
            paddingValue = 0
        ) {

        }

    }
}