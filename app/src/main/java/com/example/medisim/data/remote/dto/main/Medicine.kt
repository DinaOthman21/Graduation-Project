package com.example.medisim.data.remote.dto.main

import com.google.gson.annotations.SerializedName

data class Medicine(
    @SerializedName("image")
    val imageLink:String ,
    @SerializedName("name_en")
    val medicineEnName:String,
    @SerializedName("name_Ar")
    val medicineArName:String,
    @SerializedName("scientific_name_en")
    val enScientificName:String,
    @SerializedName("scientific_name_ar")
    val arScientificName:String,
    @SerializedName("classification_en")
    val enMedicineClassification:String,
    @SerializedName("classification_ar")
    val arMedicineClassification:String,
    @SerializedName("category_en")
    val enCategory:String,
    @SerializedName("category_ar")
    val arCategory:String,
    @SerializedName("description_en")
    val enDescription:String,
    @SerializedName("description_ar")
    val arDescription:String
)
