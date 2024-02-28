package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medisim.presentation.components.BackIcon
import com.example.medisim.presentation.components.Post
import com.example.medisim.presentation.components.TextLabel
import com.example.medisim.presentation.components.TextTitle
import com.example.medisim.presentation.components.ViewImage
import com.example.medisim.ui.theme.brush


@Composable
fun PostDetails(post: Post,navController: NavHostController) {
    Column(
        modifier = Modifier.padding(12.dp),
    ) {
        Row(
            modifier = Modifier.padding(bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackIcon{ navController.popBackStack()}
        }
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            TextLabel(
                text = "post title",
                textFont = 22,
                textFontWight = FontWeight.Bold
            )
        }
        ViewImage(
            image = post.image,
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .padding(vertical = 15.dp),
        )
        TextTitle(
            text = "Post description here and all content will be here ew kalam keter awy 3shan ana msh fady, Post description here and all content will be here ew kalam keter awy 3shan ana msh fady" +
                    "Post description here and all content will be here ew kalam keter awy 3shan ana msh fady, Post description here and all content will be here ew kalam keter awy 3shan ana msh fady",
            maxLines = 500
            )



    }
}