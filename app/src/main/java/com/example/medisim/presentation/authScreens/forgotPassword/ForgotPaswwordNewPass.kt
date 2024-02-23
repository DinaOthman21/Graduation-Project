package com.example.medisim.presentation.authScreens.forgotPassword


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medisim.R
import com.example.medisim.presentation.components.ButtonClickOn
import com.example.medisim.presentation.components.PasswordEditText
import com.example.medisim.presentation.components.TextLabel
import com.example.medisim.presentation.navigation.Screens
import com.example.medisim.ui.theme.brush


@Composable
fun ForgotPasswordNewPassword(navController: NavHostController) {


    Column(
        modifier = Modifier.padding(12.dp),
    ) {
        Row(
            modifier = Modifier.padding(bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Arrow back",
                Modifier
                    .size(50.dp)
                    .padding(5.dp)
                    .background(brush, shape = CircleShape)
                    .clickable {
                        navController.popBackStack()
                    },
                tint = MaterialTheme.colorScheme.primary
            )

        }

        TextLabel(
            text = stringResource(R.string.create_new_password),
            modifier = Modifier.padding(top = 20.dp),
            textFont = 28,
            textFontWight = FontWeight.Bold
        )
        TextLabel(
            text = stringResource(id = R.string.password),
            modifier = Modifier.padding(top = 60.dp,bottom = 20.dp),
            textFont = 18,
            textFontWight = FontWeight.Bold
        )
        PasswordEditText(
            password = "",
            isErrorPassword = false,
            passwordErrorMessage = "",
            showPassword = false,
            onValueChange = {}
        ) {

        }
        TextLabel(
            text = stringResource(R.string.confirm_password),
            modifier = Modifier.padding(top = 20.dp,bottom = 20.dp),
            textFont = 18,
            textFontWight = FontWeight.Bold
        )
        PasswordEditText(
            password = "",
            isErrorPassword = false,
            passwordErrorMessage = "",
            showPassword = false,
            onValueChange = {}
        ) {

        }
        Spacer(modifier = Modifier.weight(1f))
        ButtonClickOn(
            buttonText = stringResource(R.string.save),
            paddingValue = 0) {
            navController.navigate(Screens.Home.route)

        }



    }
}