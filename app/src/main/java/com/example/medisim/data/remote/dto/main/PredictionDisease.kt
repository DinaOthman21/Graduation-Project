package com.example.medisim.data.remote.dto.main

data class PredictionDisease(
    val enDiseaseName:String,
    val arDiseaseName:String,
    val confidence:Double,
    val enDiseaseDescription:String,
    val arDiseaseDescription:String,
    val enAdvices:List<String>,
    val arAdvices:List<String>,
)
