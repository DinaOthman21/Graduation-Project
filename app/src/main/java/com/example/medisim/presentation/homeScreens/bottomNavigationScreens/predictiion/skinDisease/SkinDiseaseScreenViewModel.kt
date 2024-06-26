package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.skinDisease


import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medisim.domain.repository.ApiServicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SkinDiseaseScreenViewModel @Inject constructor(private val repo:ApiServicesRepository): ViewModel() {

    private var _state by mutableStateOf(SkinDiseaseState())
    val state: State<SkinDiseaseState>
        get() = derivedStateOf { _state }


    fun onSelectImage(bitmap:Bitmap?){
        _state = _state.copy(
            image = bitmap
        )
    }

    fun onDialogDismiss(){
        _state = _state.copy(
            dialogState = false
        )
    }

    fun onDetectClick(context: Context){
        viewModelScope.launch(Dispatchers.IO){
            val imagePart = prepareFilePart(context,_state.image!!)
            val result = repo.skinDetect(imagePart)
            _state = _state.copy(
                skinDiseaseResponse = result,
                dialogState = true
            )

        }
    }
    private fun prepareFilePart(context: Context, bitmap: Bitmap): MultipartBody.Part {
        val file = bitmapToFile(bitmap, context)
        val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("image", file.name, requestFile)
    }



    private fun bitmapToFile(bitmap: Bitmap, context: Context): File {
        // Get the cache directory
        val cacheDir = context.cacheDir

        // Create a temporary file
        val file = File(cacheDir, "temp_image.jpg")
        file.createNewFile()

        // Convert bitmap to byte array
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)
        val bitmapData = bos.toByteArray()

        // Write the byte array to the file
        try {
            val fos = FileOutputStream(file)
            fos.write(bitmapData)
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return file
    }




}