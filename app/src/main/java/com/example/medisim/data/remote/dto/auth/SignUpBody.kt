package com.example.medisim.data.remote.dto.auth


data class SignUpBody(
    val userName:String,
    val email: String,
    val password:String,
    val height:Double,
    val weight:Double,
    val age:Int,
    val isMale:Boolean,
    val selectedChronic:List<Int>


)