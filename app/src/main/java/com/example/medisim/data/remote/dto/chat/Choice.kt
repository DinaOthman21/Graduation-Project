package com.example.medisim.data.remote.dto.chat

data class Choice(
    val finish_reason: String,
    val index: Int,
    val logprobs: Any,
    val message: Message
)