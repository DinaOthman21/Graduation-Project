package com.example.medisim.presentation.homeScreens.topNavigationScreens.chatAI

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medisim.R
import com.example.medisim.presentation.components.EditTextWithIcon
import com.example.medisim.presentation.components.LottieAnimationShow
import com.example.medisim.presentation.components.TextLabel
import com.example.medisim.presentation.components.TextWithBackgroundColorAsCard
import com.example.medisim.ui.theme.brush

data class Message(val message:String,val isUser:Boolean)

//val messages = listOf<Message>()
val messages = listOf(
    Message("Hello chat",true),
    Message("Hello User, you are welcome, Hello User, you are welcome, Hello User, you are welcome",false),
    Message("how are you to day? how are you to day? how are you to day?",true),
    Message("i am fine, what about you!!",false),
    Message("مرحبا بك",true),
    Message("Hello chat",true),
    Message("Hello User, you are welcome, Hello User, you are welcome, Hello User, you are welcome",false),
    Message("how are you to day? how are you to day? how are you to day?",true),
    Message("i am fine, what about you!!",false),
    Message("مرحبا بك",true),

)

@Composable
fun ChatScreen(navController:NavHostController) {
    Column{
        Box(modifier = Modifier.weight(0.1f)){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 5.dp,
                        bottom = 10.dp,
                        start = 12.dp,
                        end = 12.dp
                    ),
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
                Spacer(modifier = Modifier.width(90.dp))
                TextLabel(
                    text = stringResource(R.string.ai_chat),
                    textFont = 26,
                    textFontWight = FontWeight.Bold
                )

            }
        }

        if (messages.isEmpty()){
            Box(
                modifier = Modifier.fillMaxWidth().weight(0.8f),
                contentAlignment = Alignment.Center
                ){
                LottieAnimationShow(
                    animationResId = R.raw.chat_animation,
                    size = 320,
                    padding = 12,
                    paddingBottom = 0
                )
            }

        }else{
            Box(modifier = Modifier.weight(0.8f)){
                LazyColumn(
                    modifier = Modifier
                        .padding(
                            start = 12.dp,
                            end = 12.dp
                        ),
                ){
                    items(messages){ message->
                        // handel if message from user then make it in right side otherwise left side.
                        if (message.isUser) {
                            Row (
                                modifier = Modifier.fillMaxSize()
                            ){
                                Spacer(modifier = Modifier.weight(1f))
                                Spacer(modifier = Modifier.width(50.dp))
                                TextWithBackgroundColorAsCard(
                                    text = message.message,
                                    cardCornerTopEnd =  0 ,
                                    cardCornerTopStart = 24,
                                    cardCornerBottomEnd = 24,
                                    cardCornerBottomStart = 0,
                                )
                            }
                        }
                        else{
                            TextWithBackgroundColorAsCard(
                                text = message.message,
                                modifier = Modifier.width(320.dp),
                                backgroundColor = MaterialTheme.colorScheme.tertiary,
                                cardCornerTopEnd = 24,
                                cardCornerTopStart = 0,
                                cardCornerBottomEnd = 0,
                                cardCornerBottomStart = 24
                            )
                        }

                    }

                }
            }
        }

        Box(modifier = Modifier.weight(0.1f)){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(MaterialTheme.colorScheme.background),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ){
                EditTextWithIcon(
                    text = "",
                    placeholderID = R.string.message,
                    iconID = R.drawable.baseline_send_24,
                    editTextWidth = 370,
                    roundedCornerShapeValue = 28,
                    onIconButtonClick = { /*TODO*/ },
                    onValueChange = {}
                )



            }
        }
    }
}