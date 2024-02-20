package com.example.medisim.presentation.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medisim.R


@Composable
fun TextWithHiatusFont(
    text: String,
    modifier: Modifier = Modifier,
    textFont: Int = 18,
    textColor: Color = MaterialTheme.colorScheme.primary
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = textFont.sp,
        fontFamily = FontFamily(Font(R.font.hiatus2)),
        color = textColor
    )

}

@Composable
fun AppNameWithHiatusFont(modifier: Modifier = Modifier) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color(0xFF097AC7))) {
                append("MediSim")
            }
            append(" ")
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                append("AI")
            }
        },
        modifier = modifier,
        fontSize = 90.sp,
        fontFamily = FontFamily(Font(R.font.hiatus2)),
    )

}


@Composable
fun TextLabel(
    text: String,
    modifier: Modifier = Modifier,
    textFont: Int = 12,
    textFontWight: FontWeight = FontWeight.Normal,
    textColor: Color = MaterialTheme.colorScheme.primary
) {

    Text(
        text = text,
        modifier = modifier,
        color = textColor,
        fontSize = textFont.sp,
        fontWeight = textFontWight,
    )

}



@Composable
fun TextTitle(
    text:String,
    modifier: Modifier = Modifier,
    textColor:Color = MaterialTheme.colorScheme.primary,
    textFont: Int = 18,
    textFontWight: FontWeight = FontWeight.Normal,
    maxLines: Int = 1,
    textLetterSpacing: Double = 0.5,
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = textFont.sp,
        fontWeight = textFontWight,
        color = textColor,
        letterSpacing = textLetterSpacing.sp,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Justify,
        maxLines = maxLines,
    )

}



@Composable
fun TextWithBoldUnderLine(
    text:String,
    modifier: Modifier = Modifier,
    textColor:Color = MaterialTheme.colorScheme.primary,
    textFont: Int = 22,
    textLetterSpacing: Double = 1.5,
    lineColor: Color = Color.Black,

    ) {
    Column {
        Text(
            text = text,
            modifier = modifier.padding(horizontal = 4.dp),
            fontSize = textFont.sp,
            color = textColor,
            fontWeight = FontWeight.Bold,
            letterSpacing = textLetterSpacing.sp

        )
        Divider(
            modifier = Modifier
                .padding(4.dp)
                .height(4.dp)
                .width(36.dp),
            color = lineColor
        )
    }
}