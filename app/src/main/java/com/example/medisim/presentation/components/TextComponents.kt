package com.example.medisim.presentation.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HighlightOff
import androidx.compose.material.icons.outlined.HighlightOff
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.example.medisim.ui.theme.CommonComponent2


@Composable
fun TextWithHiatusFont(
    text: String,
    modifier: Modifier = Modifier,
    textFont: Int = 26,
    textColor: Color = MaterialTheme.colorScheme.primary
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = textFont.sp,
        fontFamily = FontFamily(Font(R.font.abel)),
        color = textColor,
        fontWeight = FontWeight.Bold,
    )

}

@Composable
fun AppNameWithHiatusFont(
    modifier: Modifier = Modifier,
    fontSize:Int = 90,
    colorText:Color = Color(0xFF097AC7),
    showAiString:Boolean) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = colorText)) {
                append("MediSim")
            }
            if (showAiString){
                append(" ")
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append("AI")
                }
            }

        },
        modifier = modifier,
        fontSize = fontSize.sp,
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


@Composable
fun TextWithBackgroundColorAsCard(
    text:String,
    modifier: Modifier = Modifier,
    textColor:Color = MaterialTheme.colorScheme.primary,
    backgroundColor:Color = CommonComponent2,
    textFont: Int = 22,
    textFontWight: FontWeight = FontWeight.Normal,
    textLetterSpacing: Double = 1.5,
    cardCornerTopEnd:Int = 16,
    cardCornerTopStart:Int = 16,
    cardCornerBottomEnd:Int = 16,
    cardCornerBottomStart:Int = 16,
) {
    Card (
        modifier = modifier
            .padding(12.dp,top = 30.dp, bottom = 5.dp)
            ,
        elevation = 16.dp,
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(
            topEnd = cardCornerTopEnd.dp,
            topStart = cardCornerTopStart.dp,
            bottomStart = cardCornerBottomStart.dp,
            bottomEnd = cardCornerBottomEnd.dp
        )
        ){
        Text(
            text = text,
            modifier = modifier.padding(horizontal = 8.dp, vertical = 10.dp),
            fontSize = textFont.sp,
            color = textColor,
            fontWeight = textFontWight,
            letterSpacing = textLetterSpacing.sp
        )

    }

}



@Composable
fun CustomChip(
    text:String,
    modifier: Modifier = Modifier,
    textColor:Color = MaterialTheme.colorScheme.primary,
    backgroundColor:Color = CommonComponent2,
    textFont: Int = 14,
    textFontWight: FontWeight = FontWeight.Normal,
    textLetterSpacing: Double = 1.5,
    onIconClick:(String)->Unit
) {
    Card (
        modifier = modifier
            .padding(6.dp)
        ,
        elevation = 16.dp,
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(16.dp)
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = text,
                modifier = modifier.padding(horizontal = 8.dp, vertical = 10.dp),
                fontSize = textFont.sp,
                color = textColor,
                fontWeight = textFontWight,
                letterSpacing = textLetterSpacing.sp
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.Default.HighlightOff,
                modifier = Modifier.size(30.dp).padding(end = 5.dp).clickable {
                    onIconClick(text)
                },
                contentDescription = "",
                tint = textColor
            )
        }
    }

}