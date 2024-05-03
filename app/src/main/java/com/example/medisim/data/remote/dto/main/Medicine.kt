package com.example.medisim.data.remote.dto.main

data class Medicine(
    val imageLink:String ,
    val medicineEnName:String,
    val medicineArName:String,
    val enScientificName:String,
    val arScientificName:String,
    val enMedicineClassification:String,
    val arMedicineClassification:String,
    val enCategory:String,
    val arCategory:String,
    val enDescription:String,
    val arDescription:String
)
