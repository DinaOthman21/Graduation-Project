package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.mediclaTest

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


class MedicalTestScreenViewModel : ViewModel() {
    private var _state by mutableStateOf<File?>(null)
    val state: State<File?>
        get() = derivedStateOf { _state }


    fun onSelectFile(file: File?){
        _state = file
    }


    // Function to get a file from URI
    fun getFileFromUri(context: Context, uri: Uri): File? {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        inputStream?.let { input ->
            val file = createTemporalFileFrom(context, input)
            input.close()
            return file
        }
        return null
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

}


