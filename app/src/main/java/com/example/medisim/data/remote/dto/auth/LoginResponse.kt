package com.example.medisim.data.remote.dto.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token:String,
    @SerializedName("userName")
    val userName:String
)
