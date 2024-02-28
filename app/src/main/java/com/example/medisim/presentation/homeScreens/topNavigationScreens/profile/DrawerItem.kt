package com.example.medisim.presentation.homeScreens.topNavigationScreens.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.outlined.ContactPage
import androidx.compose.material.icons.outlined.ModeNight
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DrawerItem(
    val imageVector: ImageVector,
    val title:String,
    val color:Long
) {
    data object Language : DrawerItem(
        imageVector = Icons.Default.Language,
        title = "Language",
        color = 0xFF942BB9
    )
    data object Mode : DrawerItem(
        imageVector = Icons.Outlined.ModeNight,
        title = "Dark Mode",
        color = 0xFFCADF4A

    )

    data object TermsAndConditions : DrawerItem(
        imageVector = Icons.Outlined.ContactPage,
        title = "Terms & Conditions",
        color = 0xFFCC2828

    )

    data object License : DrawerItem(
        imageVector = Icons.Default.Language,
        title = "License",
        color = 0xFFC62887

    )

    data object Rate : DrawerItem(
        imageVector = Icons.Outlined.Star,
        title = "Rate this app",
        color = 0xFF02457A

    )

    data object Share : DrawerItem(
        imageVector = Icons.Default.Language,
        title = "Share this app",
        color = 0xFF1B9484

    )

    data object Logout : DrawerItem(
        imageVector = Icons.AutoMirrored.Filled.Logout,
        title = "Log Out",
        color = 0xFF000000

    )

}