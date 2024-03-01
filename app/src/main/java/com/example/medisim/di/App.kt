package com.example.medisim.di

import android.app.Application
import com.example.medisim.domain.SharedPreferences
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class App : Application() {}