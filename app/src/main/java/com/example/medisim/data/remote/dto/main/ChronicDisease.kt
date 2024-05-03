package com.example.medisim.data.remote.dto.main

data class ChronicDisease(
    val id:Int,
    val enName:String,
    val arName:String,
    var isSelected:Boolean = false
)