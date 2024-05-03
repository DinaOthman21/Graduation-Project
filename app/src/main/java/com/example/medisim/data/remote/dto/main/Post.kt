package com.example.medisim.data.remote.dto.main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val enTitle:String,
    val arTitle:String,
    val enDescription:String,
    val arDescription:String,
    val imgLink:String,
    val isAdvice:Boolean
):Parcelable