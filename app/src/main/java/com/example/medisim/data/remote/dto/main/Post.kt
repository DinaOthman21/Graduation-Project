package com.example.medisim.data.remote.dto.main

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    @SerializedName("title_en")
    val enTitle:String,
    @SerializedName("title_ar")
    val arTitle:String,
    @SerializedName("description_en")
    val enDescription:String,
    @SerializedName("description_ar")
    val arDescription:String,
    @SerializedName("image")
    val imgLink:String,
    @SerializedName("isAdvice")
    val isAdvice:Boolean
):Parcelable