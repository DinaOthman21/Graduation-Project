package com.example.medisim.presentation.authScreens.signUp

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medisim.R
import com.example.medisim.presentation.components.ButtonClickOn
import com.example.medisim.presentation.components.EmailEditText
import com.example.medisim.presentation.components.PasswordEditText
import com.example.medisim.presentation.components.TextLabel
import com.example.medisim.presentation.components.UserNameEditText
import com.example.medisim.presentation.navigation.Screens
import com.example.medisim.ui.theme.brush

@Composable
fun SignUpScreen(navController: NavHostController,signUpScreenViewModel: SignUpScreenViewModel) {

    val state = signUpScreenViewModel.state.value
    val context = LocalContext.current


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
            text = stringResource(R.string.create_new_account),
            modifier = Modifier.padding(top = 10.dp),
            textFont = 28,
            textFontWight = FontWeight.Bold
        )
        TextLabel(
            text = stringResource(R.string.enter_the_required_data_below),
            modifier = Modifier.padding(top = 10.dp),
            textFont = 18,
            textColor = MaterialTheme.colorScheme.secondary
        )
        TextLabel(
            text = stringResource(R.string.full_name),
            modifier = Modifier.padding(top = 30.dp,bottom = 10.dp),
            textFont = 18,
            textFontWight = FontWeight.Bold
        )
        UserNameEditText(
            userName = state.userName,
            isUserNameError = state.isErrorUserName,
            userNameErrorMessage = state.userNameErrorMessage,
            onValueChange = {newUserName->signUpScreenViewModel.onUserNameChange(newUserName)}
        )
        TextLabel(
            text = stringResource(id = R.string.email),
            modifier = Modifier.padding(bottom = 10.dp),
            textFont = 18,
            textFontWight = FontWeight.Bold
        )
        EmailEditText(
            email = state.email,
            isErrorEmail = state.isErrorEmail,
            emailErrorMessage = state.emailErrorMessage,
            onValueChange = {newEmail->signUpScreenViewModel.onEmailChange(newEmail)}
        )

        TextLabel(
            text = stringResource(id = R.string.password),
            modifier = Modifier.padding(bottom = 10.dp),
            textFont = 18,
            textFontWight = FontWeight.Bold
        )
        PasswordEditText(
            password = state.password,
            isErrorPassword = state.isErrorPassword,
            passwordErrorMessage = state.passwordErrorMessage,
            showPassword = state.showPassword,
            onValueChange = {newPassword->signUpScreenViewModel.onPasswordChange(newPassword)}
        ) {signUpScreenViewModel.onIconShowPassword()}
        TextLabel(
            text = stringResource(R.string.confirm_password),
            modifier = Modifier.padding(bottom = 10.dp),
            textFont = 18,
            textFontWight = FontWeight.Bold
        )
        PasswordEditText(
            password = state.confirmPassword,
            isErrorPassword = state.isErrorConfirmPassword,
            passwordErrorMessage = state.confirmPasswordErrorMessage,
            showPassword = state.showConfirmPassword,
            onValueChange = {newConfirmPassword->signUpScreenViewModel.onConfirmPasswordChange(newConfirmPassword)}
        ) {signUpScreenViewModel.onIconShowConfirmPassword()}
        Spacer(modifier = Modifier.weight(1f))
        ButtonClickOn(
            buttonText = stringResource(R.string.next),
            paddingValue = 0) {
            signUpScreenViewModel.onNextToSecondScreen(navController,context)
        }



    }
}