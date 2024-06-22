package com.example.medisim.data.remote

import com.example.medisim.data.remote.dto.auth.LoginResponse
import com.example.medisim.data.remote.dto.auth.SignUpResponse
import com.example.medisim.data.remote.dto.main.ChronicDisease
import com.example.medisim.data.remote.dto.main.MedicalTestResponse
import com.example.medisim.data.remote.dto.main.Medicine
import com.example.medisim.data.remote.dto.main.Post
import com.example.medisim.data.remote.dto.main.PredictionDisease
import com.example.medisim.data.remote.dto.main.SkinDiseaseResponse
import com.example.medisim.data.remote.dto.main.Symptom
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

//    @POST("login.json")
//    suspend fun login( @Body requestBody: LoginBody): LoginResponse
    @GET("login.json")
    suspend fun login(): Response<LoginResponse>


//    @POST("signUp.json")
//    suspend fun signUp(@Body requestBody: SignUpBody): SignUpResponse
    @GET("signUp.json")
    suspend fun signUp(): Response<SignUpResponse>


    @GET("chronicDiseases.json")
    suspend fun getChronicDiseases(): Response<List<ChronicDisease>>


//    @GET("posts.json")
//    suspend fun getPosts(@Query("token") token:String): List<POST>
    @GET("posts.json")
    suspend fun getPosts(): Response<List<Post>>

    @GET("symptoms.json")
    suspend fun getSymptoms(): Response<List<Symptom>>


//    @GET("predictionResult.json")
//    suspend fun prediction(@Body requestBody: PredictionDiseaseBody): PredictionDiseaseResponse


    @GET("predictionResult.json")
    suspend fun predict(): Response<List<PredictionDisease>>


//    @GET("skinDetection.json")
//    suspend fun skinDetection(): SkinDiseaseResponse
//

    @GET("skinDetection.json")
    suspend fun skinDetection(): Response<SkinDiseaseResponse>


//    @GET("testResult.json")
//    suspend fun getTestResult(): MedicalTestResponse
    @GET("testResult.json")
    suspend fun getTestResult(): Response<MedicalTestResponse>


//    @GET("medicineSearch.json")
//    suspend fun search( @Query("query") SearchedItem : String): Medicine
    @GET("medicineSearch.json")
    suspend fun search(): Response<Medicine>


}