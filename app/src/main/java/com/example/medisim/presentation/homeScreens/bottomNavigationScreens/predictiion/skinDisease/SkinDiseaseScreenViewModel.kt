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
            if (result != null) {
                _state = _state.copy(
                    recommendation = getRecommendation(result.enDiseaseName),
                    skinDiseaseResponse = result,
                    dialogState = true
                )
            }

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





    private fun getRecommendation(diseaseName: String): Pair<String, String> {
        val recommendations = mapOf(
            "actinic keratosis" to Pair(
                "Use prescribed medications and protect your skin from the sun with high SPF sunscreen.",
                "استخدم الأدوية الموصوفة واحمِ بشرتك من الشمس باستخدام واقي شمس بعامل حماية عالٍ."
            ),
            "basal cell carcinoma" to Pair(
                "Follow your dermatologist’s treatment plan and minimize sun exposure.",
                "اتبع خطة علاج طبيب الأمراض الجلدية وقلل من التعرض للشمس."
            ),
            "dermatofibroma" to Pair(
                "Monitor the lesion for changes and consult your doctor if it becomes symptomatic.",
                "راقب الآفة لأي تغييرات واستشر طبيبك إذا أصبحت عرضية."
            ),
            "melanoma" to Pair(
                "Follow your oncologist’s treatment recommendations and schedule frequent follow-ups.",
                "اتبع توصيات طبيب الأورام للعلاج وجدول المتابعات المتكررة."
            ),
            "nevus" to Pair(
                "Regularly check for changes in size, shape, or color, and protect from sun exposure.",
                "افحص الوحمة بانتظام لأي تغييرات في الحجم أو الشكل أو اللون، واحمِها من الشمس."
            ),
            "pigmented benign keratosis" to Pair(
                "Regularly consult your dermatologist and monitor the lesion for changes.",
                "استشر طبيب الأمراض الجلدية بانتظام وراقب الآفة لأي تغييرات."
            ),
            "seborrheic keratosis" to Pair(
                "These lesions are benign; avoid scratching and see your doctor if they become irritated.",
                "هذه الآفات حميدة؛ تجنب الحك واستشر طبيبك إذا أصبحت متهيجة."
            ),
            "squamous cell carcinoma" to Pair(
                "Follow your treatment plan and protect your skin from UV radiation.",
                "اتبع خطة العلاج واحمِ بشرتك من الأشعة فوق البنفسجية."
            ),
            "vascular lesion" to Pair(
                "Regularly monitor the lesion and protect it from injury.",
                "راقب الآفة بانتظام واحمها من الإصابات."
            )
        )

        return recommendations[diseaseName.toLowerCase()] ?: Pair(
            "Disease not found. Please consult a healthcare provider.",
            "المرض غير موجود. يرجى استشارة مقدم الرعاية الصحية."
        )
    }



}