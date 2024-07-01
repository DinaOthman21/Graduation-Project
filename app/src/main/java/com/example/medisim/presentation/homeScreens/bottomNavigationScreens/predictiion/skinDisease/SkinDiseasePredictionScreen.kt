package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.skinDisease

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.medisim.R
import com.example.medisim.presentation.components.ButtonClickOn
import com.example.medisim.presentation.components.LottieAnimationShow
import com.example.medisim.presentation.components.ResultPredictionDialog
import com.example.medisim.presentation.components.SkinDialogContent
import com.example.medisim.presentation.components.TextLabel
import com.example.medisim.ui.theme.brush


fun setImage(url: Uri,context:Context,onImageSelected:(Bitmap)->Unit){
    var bitmap:Bitmap?

    bitmap = if (Build.VERSION.SDK_INT < 28) {
        MediaStore.Images
            .Media.getBitmap(context.contentResolver,url)

    } else {
        val source = ImageDecoder
            .createSource(context.contentResolver,url)
        ImageDecoder.decodeBitmap(source)
    }

    bitmap?.let {  btm ->
        onImageSelected(btm)
    }
}

@Composable
fun SkinDiseaseScreen(skinDiseaseViewModel: SkinDiseaseScreenViewModel) {
    val state = skinDiseaseViewModel.state.value
    val context = LocalContext.current

    // Now can access resources using the context
    val resources = context.resources
    val isArabicLang = resources.configuration.locales[0].language == "ar"



    // to upload image from mobile
    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {

            // set image in viewModel
            setImage(it, context ){btm->
                skinDiseaseViewModel.onSelectImage(btm)
            }
        }
    }

    val takePictureLauncher  = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {result->
        if (result.resultCode == RESULT_OK){
            result.data?.extras?.get("data")?.let { bitmap ->
                // Handle the captured image here, you may convert it to a bitmap
                val imageBitmap = (bitmap as? Bitmap)?.asImageBitmap()?.asAndroidBitmap()
                // Update the UI or do something with the imageBitmap
                skinDiseaseViewModel.onSelectImage(imageBitmap)
            }

        }

    }

    // request permission for camera
    val requestPermissionLauncher   = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) {isGranted->
        if (isGranted){
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePictureLauncher.launch(intent)
        }

    }



    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ){

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            LottieAnimationShow(
                animationResId = R.raw.predic,
                size = 200,
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
                    launcher.launch("image/*")
                },
            verticalAlignment = Alignment.CenterVertically
        ){
            TextLabel(
                text = stringResource(R.string.upload_skin_disease_image),
                textFont = 16
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    takePictureLauncher.launch(intent)
                }
                else {
                    requestPermissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }) {
                Icon(
                    imageVector = Icons.Default.PhotoCamera,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

        }
        AnimatedVisibility(visible = state.image != null) {
            Image(
                bitmap = state.image!!.asImageBitmap(),
                contentDescription ="",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(260.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(brush = brush)
                    .shadow(
                        elevation = 24.dp,
                        spotColor = Color(0xFF000718),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentScale = ContentScale.Crop,
            )
        }

        // selected items here
        Spacer(modifier = Modifier.weight(1f))
        ButtonClickOn(
            buttonText = stringResource(R.string.detect),
            modifier = Modifier.padding(bottom = 41.dp),
            paddingValue = 0
        ) {
            if (state.image == null){
                Toast.makeText(context, context.getString(R.string.please_scan_or_select_you_image),Toast.LENGTH_SHORT).show()
            }else{
                skinDiseaseViewModel.onDetectClick(context)
            }

        }

        AnimatedVisibility(visible = state.dialogState) {
            state.skinDiseaseResponse?.let {
                ResultPredictionDialog(content = {
                    SkinDialogContent(
                        diseaseName = if (isArabicLang) it.arDiseaseName
                        else it.enDiseaseName,
                        diseaseDescription = if (isArabicLang) it.arDiseaseDescription
                        else it.enDiseaseDescription
                    ) {
                        skinDiseaseViewModel.onDialogDismiss()
                    }
                }, image = R.drawable.skin) {
                    skinDiseaseViewModel.onDialogDismiss()
                }
            }
        }

    }
}



