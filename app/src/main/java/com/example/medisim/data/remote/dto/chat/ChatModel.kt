package com.example.medisim.data.remote.dto.chat

data class ChatModel(
    val choices: List<Choice>,
    val created: Int,
    val id: String,
    val system_fingerprint: String,
    val model: String,
    val `object`: String,
    val usage: Usage
)