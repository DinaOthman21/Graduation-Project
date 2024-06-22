package com.example.medisim.data.repository

import android.util.Log
import com.example.medisim.data.Constants
import com.example.medisim.data.remote.ChatApiServices
import com.example.medisim.data.remote.dto.chat.ChatBody
import com.example.medisim.data.remote.dto.chat.Message
import com.example.medisim.domain.repository.ChatApiRepository
import javax.inject.Inject

class ChatApiRepositoryImpl @Inject constructor(private val api:ChatApiServices):
    ChatApiRepository {
    override suspend fun getMessage(allMessages:List<Message>):String {
        Log.d("api","from  api 1")
        var chatMessage = "some error happened please try again."


        Log.d("api","from  api 2")

        val requestBody =  ChatBody(
            messages = allMessages,
            model = Constants.CHAT_MODEL
        )

        Log.d("api","from  api 3")

        val  contentType = "application/json"
        Log.d("api","from  api 4")

        val authorization="Bearer ${Constants.CHAT_API_KEY}"
        Log.d("api","from  api 5")

        try {
            Log.d("api","from  api 6")

            val response = api.getChat(contentType,authorization,requestBody)
            Log.d("api","from  api 7")

            chatMessage = response.choices.first().message.content
            Log.d("api","from  api 8")


        }catch (e:Exception){
            e.printStackTrace()
            Log.d("api","from api")
            return chatMessage
        }

        return chatMessage

    }


    private fun isArabic(text:String): Boolean {
        for (char in text) {
            val codePoint = char.code
            if (codePoint in 0x0600..0x06FF || // Arabic
                codePoint in 0x0750..0x077F || // Arabic Supplement
                codePoint in 0x08A0..0x08FF || // Arabic Extended-A
                codePoint in 0xFB50..0xFDFF || // Arabic Presentation Forms-A
                codePoint in 0xFE70..0xFEFF || // Arabic Presentation Forms-B
                codePoint in 0x1EE00..0x1EEFF   // Arabic Mathematical Alphabetic Symbols
            ) {
                return true
            }
        }
        return false
    }

}