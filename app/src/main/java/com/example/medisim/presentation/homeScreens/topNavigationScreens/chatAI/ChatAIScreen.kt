package com.example.medisim.presentation.homeScreens.topNavigationScreens.chatAI

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medisim.R
import com.example.medisim.presentation.components.BackIcon
import com.example.medisim.presentation.components.EditTextWithIcon
import com.example.medisim.presentation.components.Keyboard
import com.example.medisim.presentation.components.LottieAnimationShow
import com.example.medisim.presentation.components.TextLabel
import com.example.medisim.presentation.components.TextWithBackgroundColorAsCard
import com.example.medisim.presentation.components.keyboardAsState

@Composable
fun ChatScreen(navController:NavHostController,chatAIViewModel: ChatAIViewModel) {
    val messages by chatAIViewModel.allMessages.collectAsState()
    val state = chatAIViewModel.state.value

    val context = LocalContext.current
    val activity = (context as? ComponentActivity)?.window?.decorView?.rootView
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager


    val isKeyboardOpen by keyboardAsState() // Keyboard.Opened or Keyboard.Closed



    Column(
        modifier = Modifier.padding( top = if (isKeyboardOpen == Keyboard.Opened) 300.dp else 0.dp )
    ){
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
            BackIcon{ navController.popBackStack()}
            Spacer(modifier = Modifier.width(90.dp))
            TextLabel(
                text = stringResource(R.string.ai_chat),
                textFont = 26,
                textFontWight = FontWeight.Bold
            )

        }
        Column (
            modifier = Modifier.weight(1f)
        ){
            if (messages.isEmpty()){
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    LottieAnimationShow(
                        animationResId = R.raw.chat_animation,
                        size = 320,
                        padding = 12,
                        paddingBottom = 0
                    )
                }

            }
            else{
                LazyColumn(
                    modifier = Modifier
                        .padding(
                            start = 12.dp,
                            end = 12.dp,
                            bottom = 12.dp,

                            ),
                ){
                    items(messages){ message->
                        // handel if message from user then make it in right side
                        // otherwise left side.
                        if (message.role == "user") {
                            Row (
                                modifier = Modifier.fillMaxSize()
                            ){
                                Spacer(modifier = Modifier.weight(1f))
                                Spacer(modifier = Modifier.width(50.dp))
                                TextWithBackgroundColorAsCard(
                                    text = message.content,
                                    cardCornerTopEnd =  0 ,
                                    cardCornerTopStart = 24,
                                    cardCornerBottomEnd = 24,
                                    cardCornerBottomStart = 0,
                                )
                            }
                        }
                        else {
                            TextWithBackgroundColorAsCard(
                                text = message.content,
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


        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(bottom = 5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ){
            EditTextWithIcon(
                text = state.message,
                placeholderID = R.string.message,
                iconID = R.drawable.baseline_send_24,
                editTextWidth = 370,
                roundedCornerShapeValue = 28,
                isIconEnabled = state.isIconEnable,
                onIconButtonClick = {
                    chatAIViewModel.sendMessage()
                    inputMethodManager.hideSoftInputFromWindow(activity?.windowToken, 0)
                },
                onValueChange = {newMessage->chatAIViewModel.onMessageChange(newMessage)}
            )
        }

    }

}



