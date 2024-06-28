package com.example.medisim.data.remote

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

    @POST("Authentication/login")
    suspend fun login( @Body requestBody: LoginBody): Response<LoginResponse>

    @POST("Authentication/signup")
    suspend fun signUp(@Body requestBody: SignUpBody): Response<SignUpResponse>

    @POST("Authentication/forgetpassword")
    suspend fun forgetPassword( @Body requestBody: ForgetPasswordRequestBody)

    @POST("Authentication/verifyotp")
    suspend fun verifyOtp( @Body requestBody: VerifyOtpRequestBody):Response<String>

    @POST("Authentication/changepassword")
    suspend fun changePassword( @Body requestBody: ChangePasswordRequestBody):Response<String>






    @GET("FetchData/getchronicdisease")
    suspend fun getChronicDiseases(): Response<List<ChronicDisease>>

    @GET("FetchData/getposts")
    suspend fun getPosts( @Header("Authorization") authorization:String):  Response<List<Post>>

    @GET("FetchData/getsymptoms")
    suspend fun getSymptoms(): Response<List<Symptom>>

    @POST("FetchData/searchfordrug")
    suspend fun search( @Query("searchTerm") searchedItem : String): Response<Medicine>





    @POST("Prediction/predictdisease")
    suspend fun predict(@Body selectedSymptomIDs:List<Int>): Response<List<PredictionDisease>>

    @Multipart
    @POST("Prediction/predictskindisease")
    suspend fun skinDetection(
    @Part image: MultipartBody.Part
        ): Response<SkinDiseaseResponse>







}