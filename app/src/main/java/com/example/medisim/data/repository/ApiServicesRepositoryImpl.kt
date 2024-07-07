package com.example.medisim.data.repository

import com.example.medisim.data.remote.ApiServices
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
import com.example.medisim.domain.repository.ApiServicesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.MultipartBody
import javax.inject.Inject


class ApiServicesRepositoryImpl @Inject constructor(
    private val api: ApiServices
) : ApiServicesRepository {
    override suspend fun login(loginData: LoginBody): LoginResponse {
        var loginResponse = LoginResponse("","")
        /// Log.d("Tag",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> login")
        try {
            val response = api.login(loginData)
            if (response.isSuccessful){
                loginResponse = response.body()!!
                // Log.d("Tag",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> login s")
            }

        }catch (e:Exception){
            e.printStackTrace()
            // Log.d("Tag",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> login f")
            return loginResponse
        }

        return loginResponse
    }

    override suspend fun signUp(signUpData: SignUpBody): SignUpResponse {
        var signUpResponse = SignUpResponse("")
        try {
            val response = api.signUp(signUpData)
            if (response.isSuccessful){
                signUpResponse = response.body()!!
            }

        }catch (e:Exception){
            e.printStackTrace()
            return signUpResponse
        }

        return signUpResponse
    }

    override suspend fun forgetPassword(forgetPasswordData: ForgetPasswordRequestBody){

        try {
            api.forgetPassword(forgetPasswordData)
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
    override suspend fun verifyOtp(verifyOtpData: VerifyOtpRequestBody):Boolean{
        var status = false
        try {
           api.verifyOtp(verifyOtpData)
            status = true
        }catch (e:Exception){
            e.printStackTrace()
            return status
        }

        return status
    }
    override suspend fun changePassword(changePasswordData: ChangePasswordRequestBody):Boolean{
        var status = false
        try {
            api.changePassword(changePasswordData)
            status = true
        }catch (e:Exception){
            e.printStackTrace()
            return status
        }

        return status
    }


    override suspend fun getChronicDiseases(): StateFlow<List<ChronicDisease>> {
        val list  = MutableStateFlow(emptyList<ChronicDisease>())
        try {
            val response = api.getChronicDiseases()
            if (response.isSuccessful){
                list.value = response.body()!!
            }

        }catch (e:Exception){
            e.printStackTrace()
            return list
        }

        return list
    }

    override suspend fun getPosts(token: String): StateFlow<List<Post>> {
        val authorization = "Bearer $token"
        val list  = MutableStateFlow(emptyList<Post>())
        try {
            val response = api.getPosts(authorization)
            if (response.isSuccessful){
                list.value = response.body()!!
            }

        }catch (e:Exception){
            e.printStackTrace()
            return list
        }

        return list
    }

    override suspend fun getSymptoms(): StateFlow<List<Symptom>> {
        val list  = MutableStateFlow(emptyList<Symptom>())
        try {
            val response = api.getSymptoms()
            if (response.isSuccessful){
                list.value = response.body()!!
            }

        }catch (e:Exception){
            e.printStackTrace()
            return list
        }

        return list
    }

    override suspend fun predict(selectedSymptomIDs:List<Int>): List<PredictionDisease>? {
        var predictionResult:List<PredictionDisease>? = null
        try {
            val response = api.predict(selectedSymptomIDs)
            if (response.isSuccessful){
                predictionResult = response.body()!!
            }

        }catch (e:Exception){
            e.printStackTrace()
            return predictionResult
        }

        return predictionResult
    }

    override suspend fun skinDetect(imagePart: MultipartBody.Part): SkinDiseaseResponse? {
        var skinDiseaseResponse:SkinDiseaseResponse? = null
        try {
            val response = api.skinDetection(imagePart)
            if (response.isSuccessful){
                skinDiseaseResponse = response.body()!!
            }

        }catch (e:Exception){
            e.printStackTrace()
            return skinDiseaseResponse
        }

        return skinDiseaseResponse
    }


    override suspend fun search(searchedItem: String): Medicine? {
        var medicine:Medicine? = null
        try {
            val response = api.search(searchedItem)
            if (response.isSuccessful){
                medicine = response.body()!!
            }

        }catch (e:Exception){
            e.printStackTrace()
            return null
        }

        return medicine
    }


}