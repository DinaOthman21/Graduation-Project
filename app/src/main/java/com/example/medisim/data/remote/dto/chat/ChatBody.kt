package com.example.medisim.data.remote.dto.chat

data class ChatBody(
    val messages: List<Message>,
    val model: String
)