package com.example.medisim.data.repository

import android.util.Log
import com.example.medisim.data.remote.ApiServices
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
import com.example.medisim.domain.repository.ApiServicesRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class ApiServicesRepositoryImpl @Inject constructor(
    private val api: ApiServices
) : ApiServicesRepository {
    override suspend fun login(loginData: LoginBody): LoginResponse {
        var loginResponse = LoginResponse("","")
        /// Log.d("Tag",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> login")
        try {
            val response = api.login()
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
            val response = api.signUp()
            if (response.isSuccessful){
                signUpResponse = response.body()!!
            }

        }catch (e:Exception){
            e.printStackTrace()
            return signUpResponse
        }

        return signUpResponse
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
        val list  = MutableStateFlow(emptyList<Post>())
        try {
            val response = api.getPosts()
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

    override suspend fun predict(predictionDiseaseBody: PredictionDiseaseBody): PredictionDiseaseResponse? {
        var predictionResult:PredictionDiseaseResponse? = null
        try {
            val response = api.predict()
            if (response.isSuccessful){
                predictionResult = response.body()!!
            }

        }catch (e:Exception){
            e.printStackTrace()
            return predictionResult
        }

        return predictionResult
    }

    override suspend fun skinDetect(skinDiseaseBody: SkinDiseaseBody): SkinDiseaseResponse? {
        var skinDiseaseResponse:SkinDiseaseResponse? = null
        try {
            val response = api.skinDetection()
            if (response.isSuccessful){
                skinDiseaseResponse = response.body()!!
            }

        }catch (e:Exception){
            e.printStackTrace()
            return skinDiseaseResponse
        }

        return skinDiseaseResponse
    }

    override suspend fun getTestResult(): MedicalTestResponse? {
        var medicalTestResponse:MedicalTestResponse? = null
        try {
            val response = api.getTestResult()
            if (response.isSuccessful){
                medicalTestResponse = response.body()!!
            }

        }catch (e:Exception){
            e.printStackTrace()
            return medicalTestResponse
        }

        return medicalTestResponse

    }

    override suspend fun search(searchedItem: String): Medicine? {
      var medicine:Medicine? = null
        try {
            val response = api.search()
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