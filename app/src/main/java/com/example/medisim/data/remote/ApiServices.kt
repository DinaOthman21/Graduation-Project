package com.example.medisim.data.remote

import com.example.medisim.data.remote.dto.auth.ChangePasswordRequestBody
import com.example.medisim.data.remote.dto.auth.ForgetPasswordRequestBody
import com.example.medisim.data.remote.dto.auth.LoginBody
import com.example.medisim.data.remote.dto.auth.LoginResponse
import com.example.medisim.data.remote.dto.auth.SignUpBody
import com.example.medisim.data.remote.dto.auth.SignUpResponse
import com.example.medisim.data.remote.dto.auth.VerifyOtpRequestBody
import com.example.medisim.data.remote.dto.main.ChronicDisease
import com.example.medisim.data.remote.dto.main.MedicalTestResponse
import com.example.medisim.data.remote.dto.main.Medicine
import com.example.medisim.data.remote.dto.main.Post
import com.example.medisim.data.remote.dto.main.PredictionDisease
import com.example.medisim.data.remote.dto.main.SkinDiseaseResponse
import com.example.medisim.data.remote.dto.main.Symptom
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiServices {

    @POST("login.json")
    suspend fun login( @Body requestBody: LoginBody): Response<LoginResponse>

    @POST("signUp.json")
    suspend fun signUp(@Body requestBody: SignUpBody): Response<SignUpResponse>

    @POST("forgetPassword.json")
    suspend fun forgetPassword( @Body requestBody: ForgetPasswordRequestBody)

    @POST("verifyOtp.json")
    suspend fun verifyOtp( @Body requestBody: VerifyOtpRequestBody):Response<String>

    @POST("changePassword.json")
    suspend fun changePassword( @Body requestBody: ChangePasswordRequestBody):Response<String>





    @GET("chronicDiseases.json")
    suspend fun getChronicDiseases(): Response<List<ChronicDisease>>

    @GET("posts.json")
    suspend fun getPosts( @Header("Authorization") authorization:String):  Response<List<Post>>

    @GET("symptoms.json")
    suspend fun getSymptoms(): Response<List<Symptom>>






    @POST("predictionResult.json")
    suspend fun predict(@Body selectedSymptomIDs:List<Int>): Response<List<PredictionDisease>>

    @Multipart
    @POST("skinDetection.json")
    suspend fun skinDetection(
    @Part image: MultipartBody.Part
        ): Response<SkinDiseaseResponse>

    @POST("medicineSearch.json")
    suspend fun search( @Query("searchTerm") searchedItem : String): Response<Medicine>





    /////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////
    // Not Use Now

    @GET("testResult.json")
    suspend fun getTestResult(): Response<MedicalTestResponse>




}