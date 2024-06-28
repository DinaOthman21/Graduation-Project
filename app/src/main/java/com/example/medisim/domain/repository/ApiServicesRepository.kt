package com.example.medisim.domain.repository

import com.example.medisim.data.remote.dto.auth.ChangePasswordRequestBody
import com.example.medisim.data.remote.dto.auth.ForgetPasswordRequestBody
import com.example.medisim.data.remote.dto.auth.LoginBody
import com.example.medisim.data.remote.dto.auth.LoginResponse
import com.example.medisim.data.remote.dto.auth.SignUpBody
import com.example.medisim.data.remote.dto.auth.SignUpResponse
import com.example.medisim.data.remote.dto.auth.VerifyOtpRequestBody
import com.example.medisim.data.remote.dto.main.ChronicDisease
import com.example.medisim.data.remote.dto.main.Medicine
import com.example.medisim.data.remote.dto.main.Post
import com.example.medisim.data.remote.dto.main.PredictionDisease
import com.example.medisim.data.remote.dto.main.SkinDiseaseResponse
import com.example.medisim.data.remote.dto.main.Symptom
import kotlinx.coroutines.flow.StateFlow
import okhttp3.MultipartBody

interface ApiServicesRepository {
    suspend fun login(loginData:LoginBody):LoginResponse
    suspend fun signUp(signUpData:SignUpBody):SignUpResponse
    suspend fun forgetPassword(forgetPasswordData: ForgetPasswordRequestBody)
    suspend fun verifyOtp(verifyOtpData: VerifyOtpRequestBody):Boolean
    suspend fun changePassword(changePasswordData: ChangePasswordRequestBody):Boolean


    suspend fun getChronicDiseases(): StateFlow<List<ChronicDisease>>
    suspend fun getPosts(token:String):StateFlow<List<Post>>
    suspend fun getSymptoms():StateFlow<List<Symptom>>



    suspend fun predict(selectedSymptomIDs:List<Int>):List<PredictionDisease>?
    suspend fun skinDetect(imagePart: MultipartBody.Part):SkinDiseaseResponse?
    suspend fun search(searchedItem:String):Medicine?




}