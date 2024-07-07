package com.example.medisim.data.remote.dto.auth

data class ChangePasswordRequestBody(
    val mail:String,
    val password:String,
    val confirmpassword:String
)
