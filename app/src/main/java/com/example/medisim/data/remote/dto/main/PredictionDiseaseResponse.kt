package com.example.medisim.data.remote.dto.main

data class PredictionDiseaseResponse(
    val enDiseaseName:String,
    val arDiseaseName:String,
    val enDiseaseDescription:String,
    val arDiseaseDescription:String,
    val enAdvices:List<String>,
    val arAdvices:List<String>,
)
