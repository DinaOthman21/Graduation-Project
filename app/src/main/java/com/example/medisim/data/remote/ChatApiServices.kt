package com.example.medisim.data.remote

import com.example.medisim.data.remote.dto.chat.ChatBody
import com.example.medisim.data.remote.dto.chat.ChatModel
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ChatApiServices {


    @POST("chat/completions")
    suspend fun getChat(
        @Header("Content-Type") contentType:String,
        @Header("Authorization") authorization:String,
        @Body requestBody: ChatBody
    ): ChatModel

}