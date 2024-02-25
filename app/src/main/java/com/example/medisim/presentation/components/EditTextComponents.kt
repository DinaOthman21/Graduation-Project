package com.example.medisim.presentation.components



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medisim.R


@Composable
fun textFieldColors() : TextFieldColors {

    return TextFieldDefaults.colors(
        // text color
        focusedTextColor = MaterialTheme.colorScheme.primary,
        unfocusedTextColor = MaterialTheme.colorScheme.primary,
        errorTextColor = MaterialTheme.colorScheme.primary,

        focusedContainerColor = MaterialTheme.colorScheme.onBackground,
        unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
        disabledContainerColor = MaterialTheme.colorScheme.onBackground,
        errorContainerColor = MaterialTheme.colorScheme.onBackground,
        // Indicator color
        cursorColor = MaterialTheme.colorScheme.primary,
        // icon color
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        focusedTrailingIconColor = MaterialTheme.colorScheme.secondary,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.secondary,
        errorTrailingIconColor = MaterialTheme.colorScheme.secondary,

    )
}





@Composable
fun EditTextWithIcon(
    text:String ,
    modifier: Modifier = Modifier,
    placeholderID: Int,
    iconID:Int,
    roundedCornerShapeValue:Int = 12,
    editTextHeight: Int = 60,
    editTextWidth: Int = 60,
    onIconButtonClick:()->Unit,
    onValueChange:(String) -> Unit) {
    TextField(
        placeholder = { Text(text = stringResource(placeholderID), fontSize = 16.sp,color = MaterialTheme.colorScheme.secondary) },
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        colors = textFieldColors(),
        shape = RoundedCornerShape(roundedCornerShapeValue.dp),
        modifier = modifier
            .height(editTextHeight.dp)
            .width(editTextWidth.dp)
            .shadow(elevation = 24.dp),
        trailingIcon = {
            IconButton(onClick = {
                onIconButtonClick()
            }) {
                Icon(
                    painter = painterResource(id = iconID),
                    contentDescription = "password icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )

}


@Composable
fun UserNameEditText(
    userName:String ,
    modifier: Modifier = Modifier,
    editTextHeight: Int = 60,
    isUserNameError:Boolean ,

    userNameErrorMessage:String,
    onValueChange:(String) -> Unit) {
    Column {
        TextField(
            placeholder = { Text(text = stringResource(R.string.user_name), fontSize = 16.sp,color = MaterialTheme.colorScheme.secondary) },
            value = userName,
            onValueChange = {
                onValueChange(it)
            },
            colors = textFieldColors(),
            shape = RoundedCornerShape(12.dp),
            modifier = modifier
                .height(editTextHeight.dp)
                .fillMaxWidth()
                .shadow(elevation = 24.dp),
            isError = isUserNameError,
        )
        Row {
            Text(
                userNameErrorMessage, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                    .padding(top = 3.dp, start = 25.dp), color = Color.Red
            )
            Spacer(modifier = Modifier.weight(1f))

        }
    }
}




@Composable
fun NumberEditText(
    number:String ,
    modifier: Modifier = Modifier,
    placeholderID: Int,
    editTextHeight: Int = 60,
    isNumberError:Boolean ,
    numberErrorMessage:String,
    onValueChange:(String) -> Unit) {
    Column {
        TextField(
            placeholder = { Text(text = stringResource(placeholderID), fontSize = 16.sp,color = MaterialTheme.colorScheme.secondary) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = number,
            onValueChange = {
                onValueChange(it)
            },
            colors = textFieldColors(),
            shape = RoundedCornerShape(12.dp),
            modifier = modifier
                .height(editTextHeight.dp)
                .fillMaxWidth()
                .shadow(elevation = 24.dp),
            isError = isNumberError,
        )
        Row {
            Text(
                numberErrorMessage, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                    .padding(top = 3.dp, start = 25.dp), color = Color.Red
            )
            Spacer(modifier = Modifier.weight(1f))

        }
    }
}



@Composable
fun EmailEditText(
    email:String ,
    modifier: Modifier = Modifier,
    editTextHeight: Int = 60,
    isErrorEmail:Boolean ,
    emailErrorMessage:String,
    onValueChange:(String) -> Unit
) {
    Column {

        TextField(
            placeholder = {
                Text(
                    text = stringResource(R.string.example_gmail_com),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {
                onValueChange(it)

            },
            colors = textFieldColors(),
            shape = RoundedCornerShape(12.dp),
            modifier = modifier
                .height(editTextHeight.dp)
                .fillMaxWidth()
                .shadow(elevation = 24.dp),
            isError = isErrorEmail,

            )

        Row {
            Text(
                emailErrorMessage, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                    .padding(top = 3.dp, start = 25.dp), color = Color.Red
            )
            Spacer(modifier = Modifier.weight(1f))

        }
    }
}


@Composable
fun PasswordEditText(
    password:String ,
    modifier: Modifier = Modifier,
    editTextHeight: Int = 60,
    isErrorPassword:Boolean ,
    passwordErrorMessage:String ,
    showPassword:Boolean,
    onValueChange:(String) -> Unit,
    onIconButtonClick:() -> Unit
) {
    Column {

        TextField(
            value = password,
            isError = isErrorPassword,
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = {
                onValueChange(it)
            },
            colors =  textFieldColors(),
            shape = RoundedCornerShape(12.dp),
            modifier = modifier
                .height(editTextHeight.dp)
                .fillMaxWidth()
                .shadow(elevation = 24.dp),
            trailingIcon = {
                IconButton(onClick = {
                    onIconButtonClick()
                }) {
                    Icon(painter = painterResource(id = if(showPassword) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24),
                        contentDescription = "password icon")
                }
            },

            )
        Row {
            Text(
                passwordErrorMessage, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                    .padding(top = 3.dp, start = 25.dp), color = Color.Red
            )
            Spacer(modifier = Modifier.weight(1f))

        }
    }

}





@Composable
fun OtpBox(
    number:String ,
    modifier: Modifier = Modifier,
    editTextHeight: Int = 80,
    editTextWidth: Int = 80,
    onValueChange:(String) -> Unit
) {
    Column {

        TextField(
            value = number,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                onValueChange(it)
            },
            colors = textFieldColors(),
            shape = RoundedCornerShape(12.dp),
            modifier = modifier
                .height(editTextHeight.dp)
                .width(editTextWidth.dp)
                .shadow(elevation = 24.dp),
            textStyle =  TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )
    }
}


@Composable
fun OtpEditText() {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        repeat(4){
            OtpBox(
                number = "1",
            ){}
            Spacer(modifier = Modifier.width(15.dp))
        }
    }
}