package com.example.medisim.data.remote.dto.auth

data class VerifyOtpRequestBody (
    val mail:String,
    val otp:String
)