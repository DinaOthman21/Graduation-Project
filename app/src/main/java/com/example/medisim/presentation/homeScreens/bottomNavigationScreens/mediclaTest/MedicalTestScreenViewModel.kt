package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.mediclaTest

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
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
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject


@HiltViewModel
class MedicalTestScreenViewModel @Inject constructor(private val repo:ApiServicesRepository): ViewModel() {
    private var _state by mutableStateOf<File?>(null)
    val state: State<File?>
        get() = derivedStateOf { _state }

    private var _fileName by mutableStateOf<String?>(null)
    val fileName: State<String?>
        get() = derivedStateOf { _fileName }

    fun onSelectFile(file: File?){
        _state = file
    }


    // Function to get a file from URI
    fun getFileFromUri(context: Context, uri: Uri): File? {
        // get file name to display it
        _fileName = getFileNameFromUri(context,uri)
        // get file
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        inputStream?.let { input ->
            val file = createTemporalFileFrom(context, input)
            input.close()
            return file
        }
        return null
    }

    private fun getFileNameFromUri(context: Context, uri: Uri): String? {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        var name: String? = null
        cursor?.use {
            if (it.moveToFirst()) {
                // Note: The column index can vary based on the device and Android version,
                // so it's safer to get it dynamically.
                val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                name = it.getString(nameIndex)
            }
        }
        return name
    }


    // Function to create a temporary file
    private fun createTemporalFileFrom(context: Context, inputStream: InputStream): File {
        val outputFile = File(context.cacheDir, "temp_file")
        outputFile.createNewFile()
        FileOutputStream(outputFile).use { outputStream ->
            val buffer = ByteArray(4 * 1024)
            var read: Int
            while (inputStream.read(buffer).also { read = it } != -1) {
                outputStream.write(buffer, 0, read)
            }
            outputStream.flush()
        }
        return outputFile
    }



    fun onUploadFile(){
        _state?.let {
            viewModelScope.launch (Dispatchers.IO){
                repo.getTestResult()
            }
        }
    }

}


