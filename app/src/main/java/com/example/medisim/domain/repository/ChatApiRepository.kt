package com.example.medisim.domain.repository

import com.example.medisim.data.remote.dto.chat.Message

interface  ChatApiRepository {
    suspend fun getMessage(allMessages:List<Message>):String
}