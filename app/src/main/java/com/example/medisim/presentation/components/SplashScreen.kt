package com.example.medisim.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medisim.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ){
        Column {
            Image(
                painter = painterResource(id = R.drawable.logo_no_back),
                modifier = Modifier
                    .padding(16.dp)
                    .size(250.dp),
                contentDescription = "splash image",

            )
            AppNameWithHiatusFont(modifier = Modifier
                .padding(
                    start = 15.dp,
                    top =35.dp,
                    bottom = 50.dp
                ),
                showAiString = false
            )
        }



    }
}