package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.mediclaTest

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medisim.R
import com.example.medisim.presentation.components.ButtonClickOn
import com.example.medisim.presentation.components.LottieAnimationShow
import com.example.medisim.presentation.components.TextLabel
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.skinDisease.setImage
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult

@Composable
fun MedicalTestScreen(medicalTestViewModel: MedicalTestScreenViewModel) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.OpenDocument()){ uri: Uri? ->
        uri?.let {
            val file = medicalTestViewModel.getFileFromUri(context,uri)
            medicalTestViewModel.onSelectFile(file)
//            file?.let {
//                Toast.makeText(context,if (it.isFile) "yes" else "no",Toast.LENGTH_LONG).show()
//            }
        }
    }


    // to scanner image using camara
    val options = GmsDocumentScannerOptions.Builder()
        .setGalleryImportAllowed(false)
        .setResultFormats(
            GmsDocumentScannerOptions.RESULT_FORMAT_JPEG,
            GmsDocumentScannerOptions.RESULT_FORMAT_PDF
        )
        .setPageLimit(2)
        .setScannerMode(GmsDocumentScannerOptions.SCANNER_MODE_FULL)
        .build()

    val scanner = GmsDocumentScanning.getClient(options)

    val scannerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) {result->
        if (result.resultCode == Activity.RESULT_OK){
            val scanningResult = GmsDocumentScanningResult.fromActivityResultIntent(result.data)

            scanningResult?.getPdf()?.let { pdf ->
                val pdfUri = pdf.getUri()
                val pageCount = pdf.getPageCount()
                Log.d("TAG--------------------->>","${pageCount.toString()}")
            }


        }

    }



    Column(
        modifier = Modifier.padding(16.dp)
    ){
        TextLabel(
            text = stringResource(R.string.medical_tests),
            textFont = 26,
            textFontWight = FontWeight.Bold
        )
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            LottieAnimationShow(
                animationResId = R.raw.files_animation,
                size = 250,
                padding = 12,
                paddingBottom = 0
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 24.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.onBackground)
                .padding(10.dp)
                .clickable {
                    launcher.launch(arrayOf("application/pdf"))
                },
            verticalAlignment = Alignment.CenterVertically
        ){
            TextLabel(
                text = stringResource(R.string.upload_test_pdf_file),
                textFont = 16
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                scanner.getStartScanIntent(context as Activity)
                    .addOnSuccessListener { intentSender->
                        scannerLauncher.launch(
                            IntentSenderRequest
                                .Builder(intentSender)
                                .build()
                        )
                    }
//                    .addOnFailureListener {
//                        Log.d("TAG",it.toString())
//                    }
            }) {
                Icon(
                    imageVector = Icons.Default.UploadFile,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

        }
        Spacer(modifier = Modifier.weight(1f))
        ButtonClickOn(
            buttonText = stringResource(R.string.upload_test),
            modifier = Modifier.padding(bottom = 25.dp),
            paddingValue = 0
        ) {

        }

    }
}