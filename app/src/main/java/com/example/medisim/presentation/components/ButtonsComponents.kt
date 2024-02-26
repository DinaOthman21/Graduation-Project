package com.example.medisim.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medisim.ui.theme.BackgroundDark
import com.example.medisim.ui.theme.CommonComponent
import com.example.medisim.ui.theme.ComponentDark
import com.example.medisim.ui.theme.animatedShimmerColor

@Composable
fun CheckboxWithName(
    checkBoxText:String,
    modifier: Modifier = Modifier,
    checkedState:Boolean,
    onToggleClick:(Boolean)->Unit
) {

    // Composable layout for the checkbox
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
        ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = { checked ->
                // Update the checked state when checkbox state changes
                onToggleClick(checked)
            },
            colors = CheckboxDefaults.colors(
                checkmarkColor = MaterialTheme.colorScheme.background
            ),
        )
        TextLabel(text = checkBoxText, textFont = 16)
    }
}


@Composable
fun ButtonClickOn(
    buttonText:String,
    modifier: Modifier = Modifier,
    buttonHeight: Int = 60,
    paddingValue:Int ,
    onButtonClick:() -> Unit ) {
    Button (
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent,),
        enabled = true,
        onClick = {onButtonClick()},
        modifier = modifier
            .padding(top = paddingValue.dp)
            .height(buttonHeight.dp)
            .fillMaxWidth(1f)
            .shadow(elevation = 24.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                animatedShimmerColor(
                    shimmerColors = listOf(
                        BackgroundDark.copy(alpha = 0.6f),
                        ComponentDark.copy(alpha = 0.5f),
                        CommonComponent.copy(alpha = 0.8f),
                    )
                )
            ),


    ){
        Text(text = buttonText, fontSize = 18.sp, style = TextStyle(color = Color.White))
    }
}



