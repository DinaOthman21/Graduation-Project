package com.example.medisim.presentation.authScreens.forgotPassword


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
import com.example.medisim.presentation.components.BackIcon
import com.example.medisim.presentation.components.ButtonClickOn
import com.example.medisim.presentation.components.OtpTextField
import com.example.medisim.presentation.components.TextLabel


@Composable
fun ForgotPasswordOto(navController: NavHostController,forgotPasswordViewModel: ForgotPasswordViewModel) {

    val state = forgotPasswordViewModel.state.value
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(12.dp),
    ) {
        Row(
            modifier = Modifier.padding(bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackIcon{ navController.popBackStack()}


        }

        TextLabel(
            text = stringResource(R.string.otp_verification),
            modifier = Modifier.padding(top = 20.dp),
            textFont = 28,
            textFontWight = FontWeight.Bold
        )
        TextLabel(
            text = stringResource(R.string.enter_the_verification_code_sent_to_mail),
            modifier = Modifier.padding(top = 60.dp,bottom = 20.dp),
            textFont = 16,
            textColor = MaterialTheme.colorScheme.secondary,
        )
        OtpTextField(
            otpText = state.otpNumber,
            otpErrorMessage = state.otpErrorMessage,
            otpLength = 6 ,
            editTextHeight = 65,
            editTextWidth = 50,
        ) {newOtp->
            forgotPasswordViewModel.onOtpCodeChange(newOtp)
            Log.d("Tag",">>>>>>>>>>>>>>>>>>>>>>>>>>>> new otp $newOtp <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")

        }

        Spacer(modifier = Modifier.weight(1f))
        ButtonClickOn(
            buttonText = stringResource(R.string.submit),
            paddingValue = 0
        ) { forgotPasswordViewModel.onSendOtpToServer(navController,context) }



    }
}