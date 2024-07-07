package com.example.medisim.data.remote.dto.main

import com.google.gson.annotations.SerializedName

data class Symptom(
    val id:Int,
    @SerializedName("name_en")
    val enName:String,
    @SerializedName("name_ar")
    val arName:String
)
