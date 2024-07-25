package com.example.medisim.presentation.homeScreens.topNavigationScreens.chatAI

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medisim.data.remote.dto.chat.Message
import com.example.medisim.domain.repository.ChatApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf

@HiltViewModel
class ChatAIViewModel @Inject constructor(private val repo: ChatApiRepository): ViewModel() {
    private  val _allMessages: MutableStateFlow<List<Message>> = MutableStateFlow(emptyList())
    val allMessages = _allMessages.asStateFlow()

    private var _state by mutableStateOf(
        ChatState()
    )

    val state: State<ChatState>
        get() = derivedStateOf { _state }

    fun onMessageChange(newMessage:String){
        _state = _state.copy(
          message = newMessage
        )
    }

    fun sendMessage(){
        // add user message to list
        val currentMessages = _allMessages.value.toMutableList()
        currentMessages.add(Message(content = _state.message, role = "user"))
        _allMessages.value = currentMessages


        // make icon send  disable, user can not make another request
        // and make edit text empty
        _state = _state.copy(
            message = "",
            isIconEnable = false
        )
        // send message to api and get response
        // add text in response to all messages as Message and make it
        // is user "false"
        viewModelScope.launch (Dispatchers.IO){
            val chatMessage = repo.getMessage(_allMessages.value)
            val currentMessages = _allMessages.value.toMutableList()
            currentMessages.add(Message(content = chatMessage, role = "system"))
            _allMessages.value = currentMessages
            // make icon send enable, user can make another request
            _state = _state.copy(
                isIconEnable = true
            )
        }
    }






}