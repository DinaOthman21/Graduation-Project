package com.example.medisim.domain.repository

import com.example.medisim.data.remote.dto.auth.LoginBody
import com.example.medisim.data.remote.dto.auth.LoginResponse
import com.example.medisim.data.remote.dto.auth.SignUpBody
import com.example.medisim.data.remote.dto.auth.SignUpResponse
import com.example.medisim.data.remote.dto.main.ChronicDisease
import com.example.medisim.data.remote.dto.main.MedicalTestResponse
import com.example.medisim.data.remote.dto.main.Medicine
import com.example.medisim.data.remote.dto.main.Post
import com.example.medisim.data.remote.dto.main.PredictionDiseaseBody
import com.example.medisim.data.remote.dto.main.PredictionDiseaseResponse
import com.example.medisim.data.remote.dto.main.SkinDiseaseBody
import com.example.medisim.data.remote.dto.main.SkinDiseaseResponse
import com.example.medisim.data.remote.dto.main.Symptom
import kotlinx.coroutines.flow.StateFlow

interface ApiServicesRepository {
    suspend fun login(loginData:LoginBody):LoginResponse
    suspend fun signUp(signUpData:SignUpBody):SignUpResponse
    suspend fun getChronicDiseases(): StateFlow<List<ChronicDisease>>
    suspend fun getPosts(token:String):StateFlow<List<Post>>
    suspend fun getSymptoms():StateFlow<List<Symptom>>
    suspend fun predict(predictionDiseaseBody: PredictionDiseaseBody):PredictionDiseaseResponse?
    suspend fun skinDetect(skinDiseaseBody: SkinDiseaseBody):SkinDiseaseResponse?
    suspend fun getTestResult():MedicalTestResponse?
    suspend fun search(searchedItem:String):Medicine?
}