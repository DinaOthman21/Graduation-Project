package com.example.medisim.presentation.components



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
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
    isIconEnabled:Boolean,
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
            IconButton(enabled = isIconEnabled,
                onClick = {
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
            singleLine = true,
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
            singleLine = true,
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
            singleLine = true,
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
            singleLine = true,
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
) {
    TextField(
        value = number,
        onValueChange = {},
        enabled = false,
        readOnly = true,
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





@Composable
fun OtpTextField(
    otpText: String,
    modifier: Modifier = Modifier,
    otpErrorMessage:String ,
    otpLength:Int = 4,
    onOtpTextChange:(String)->Unit,

) {
    // used it to check if user input is Integer number or not.
    var isValid by remember { mutableStateOf(true) }

    // make state for textField to save cursor in the end
    // by make selection equal to otpText length.
    val textFieldValueState = remember {
        mutableStateOf(
            TextFieldValue(
            text = otpText,
            selection = TextRange(otpText.length)
        ))
    }

    Column {
        BasicTextField(
            value = textFieldValueState.value,
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {


                // Validate if the new text is a valid integer
                isValid = it.text.isEmpty() || it.text.toIntOrNull() != null

                // make change if value integer and length
                // smaller than otp length.
                if (it.text.length <= otpLength && isValid){
                    onOtpTextChange(it.text)
                    textFieldValueState.value = it
                }
            },
            cursorBrush = Brush.verticalGradient(listOf(Color.Red, Color.Yellow))
        ) {
            Row (
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ){
                repeat(otpLength){index->
                    val number = when{
                        index >= otpText.length -> ""
                        else -> otpText[index]
                    }
                    OtpBox(number = number.toString()  )
                }
            }
        }

        Row {
            Text(
                otpErrorMessage, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                    .padding(top = 3.dp, start = 25.dp), color = Color.Red
            )
            Spacer(modifier = Modifier.weight(1f))

        }
    }



}





@Composable
fun DropdownMenuExample(
    selectedItem:String,
    expanded:Boolean,
    items:List<String>,
    editTextHeight:Int = 60,
    onValueChange:(String)->Unit,
    onDropDownListDismissRequest:()->Unit,
    onSelectItem:(String)->Unit
) {


    Column {
        // Dropdown menu button
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            TextField(
                value = selectedItem,
                onValueChange = {
                     onValueChange(it)
                },
                colors = textFieldColors(),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .height(editTextHeight.dp)
                    .fillMaxWidth()
                    .shadow(elevation = 24.dp),
                singleLine = true,
            )
        }

        // Dropdown menu
        DropdownMenu(
            expanded = expanded,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onBackground)
                .height(250.dp),
            onDismissRequest = { onDropDownListDismissRequest() }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onSelectItem(item)
                        onDropDownListDismissRequest()
                    }
                ) {
                    TextLabel(
                        text = item,
                        textFont = 18,
                    )
                }
            }
        }
    }
}

