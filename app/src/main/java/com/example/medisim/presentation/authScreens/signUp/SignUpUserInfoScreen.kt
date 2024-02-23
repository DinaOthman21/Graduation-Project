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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medisim.R
import com.example.medisim.presentation.components.ButtonClickOn
import com.example.medisim.presentation.components.CheckboxWithName
import com.example.medisim.presentation.components.NumberEditText
import com.example.medisim.presentation.components.TextLabel
import com.example.medisim.presentation.navigation.Screens
import com.example.medisim.ui.theme.brush


@Composable
fun SignUpUserInfoScreen(navController: NavHostController) {
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
            text = stringResource(R.string.height),
            modifier = Modifier.padding(top = 30.dp,bottom = 10.dp),
            textFont = 18,
            textFontWight = FontWeight.Bold
        )
        NumberEditText(
            number = "",
            placeholderID = R.string.enter_your_hight,
            isNumberError = false,
            numberErrorMessage = "",
            onValueChange = {}
        )
        TextLabel(
            text = stringResource(R.string.weight),
            modifier = Modifier.padding(bottom = 10.dp),
            textFont = 18,
            textFontWight = FontWeight.Bold
        )
        NumberEditText(
            number = "",
            placeholderID = R.string.enter_your_weight,
            isNumberError = false,
            numberErrorMessage = "",
            onValueChange = {}
        )
        TextLabel(
            text = stringResource(R.string.age),
            modifier = Modifier.padding(bottom = 10.dp),
            textFont = 18,
            textFontWight = FontWeight.Bold
        )
        NumberEditText(
            number = "",
            placeholderID = R.string.enter_your_age,
            isNumberError = false,
            numberErrorMessage = "",
            onValueChange = {}
        )

        TextLabel(
            text = stringResource(R.string.gender),
            modifier = Modifier.padding(bottom = 10.dp),
            textFont = 18,
            textFontWight = FontWeight.Bold
        )
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            CheckboxWithName(
                checkBoxText = stringResource(R.string.male),
                checkedState = false,
                onToggleClick = {}
            )
            CheckboxWithName(
                checkBoxText = stringResource(R.string.female),
                checkedState = false,
                onToggleClick = {}
            )

        }

        Spacer(modifier = Modifier.weight(1f))
        ButtonClickOn(
            buttonText = stringResource(R.string.next),
            paddingValue = 0) {
            navController.navigate(Screens.UserChronic.route)
        }



    }
}