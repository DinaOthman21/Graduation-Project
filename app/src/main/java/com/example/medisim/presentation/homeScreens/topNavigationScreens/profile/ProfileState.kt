package com.example.medisim.presentation.homeScreens.topNavigationScreens.profile

data class ProfileState(
    val isDark : Boolean = true,
    val isBottomSheetShow:Boolean = false,
    val isArabic:Boolean = false,
    val isRtlDirection:Boolean = false,
    val userName:String = "",
    val email:String = ""

)
