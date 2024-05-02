package com.example.medisim.presentation.authScreens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.medisim.R
import com.example.medisim.presentation.components.AppNameWithHiatusFont
import com.example.medisim.presentation.components.ButtonClickOn
import com.example.medisim.presentation.components.CheckboxWithName
import com.example.medisim.presentation.components.EmailEditText
import com.example.medisim.presentation.components.ImageButtonClick
import com.example.medisim.presentation.components.PasswordEditText
import com.example.medisim.presentation.components.TextLabel
import com.example.medisim.presentation.navigation.Screens


@Composable
fun LoginScreen(navController: NavHostController,loginViewModel: LoginScreenViewModel) {
    val state = loginViewModel.state.value
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(12.dp),
    ) {
        AppNameWithHiatusFont(modifier = Modifier
            .padding(
            start = 15.dp,
            top =35.dp,
            bottom = 20.dp
        ),
            showAiString = true
        )
        TextLabel(
            text = stringResource(R.string.email),
            modifier = Modifier.padding(bottom = 20.dp),
            textFont = 18,
            textFontWight = FontWeight.Bold
            )
        EmailEditText(
            email = state.email,
            isErrorEmail = state.isErrorEmail,
            emailErrorMessage = state.emailErrorMessage,
            onValueChange = {newEmail->loginViewModel.onEmailChange(newEmail)}
        )
        TextLabel(
            text = stringResource(R.string.password),
            modifier = Modifier.padding(top = 15.dp,bottom = 20.dp),
            textFont = 18,
            textFontWight = FontWeight.Bold
        )
        PasswordEditText(
            password = state.password,
            isErrorPassword = state.isErrorPassword,
            passwordErrorMessage = state.passwordErrorMessage,
            showPassword = state.showPassword,
            onValueChange = {newPassword->loginViewModel.onPasswordChange(newPassword)}
        ) { loginViewModel.onIconShowPassword()}

        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            CheckboxWithName(
                checkBoxText = stringResource(R.string.remember_me),
                checkedState = state.rememberMe,
                onToggleClick = {loginViewModel.onRememberMeClick()}
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(R.string.forgot_password),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier.clickable {
                    // on forgot password text click
                   navController.navigate(Screens.ForgotPassword.route)

                }
            )

        }

        ButtonClickOn(
            buttonText = stringResource(R.string.login),
            paddingValue = 30
        ) {
            // on login click
            loginViewModel.onLoginClick(navController,context)
        }


        Row(
            modifier = Modifier.padding(top = 15.dp)
        ) {
            Text(
                text = stringResource(R.string.don_t_have_account),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = stringResource(R.string.sign_up),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSecondary,
                ),
                modifier = Modifier.clickable {
                    // on sign up text click
                   navController.navigate(Screens.SignUp.route)

                }
            )
        }

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Divider(
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .width(120.dp)
                    .height(1.dp)
            )
            TextLabel(
                text = stringResource(R.string.or),
                modifier = Modifier.padding(horizontal = 2.dp),
                textColor = MaterialTheme.colorScheme.secondary,
                textFont = 14
            )
            Divider(
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .width(120.dp)
                    .height(1.dp)
            )
        }

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            ImageButtonClick(image = R.drawable.google_logo_light, paddingValue = 20) {

            }
            ImageButtonClick(image = R.drawable.google_logo_light, paddingValue = 20) {

            }
        }

    }

}