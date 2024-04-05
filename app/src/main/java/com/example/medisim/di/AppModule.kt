package com.example.medisim.di

import android.app.Application
import com.example.medisim.data.Constants
import com.example.medisim.data.remote.ChatApiServices
import com.example.medisim.data.repository.ChatApiRepositoryImpl
import com.example.medisim.domain.SharedPreferences
import com.example.medisim.domain.repository.ChatApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // general Api services
    @Provides
    @Singleton
    fun providesRetrofit (): Retrofit = Retrofit
        .Builder()
        .baseUrl(Constants.CHAT_API_SERVICES_URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesApiServices () : ChatApiServices = providesRetrofit().create(ChatApiServices::class.java)



    @Provides
    @Singleton
    fun providesApiRepository(): ChatApiRepository = ChatApiRepositoryImpl(providesApiServices())



    @Singleton
    @Provides
    fun provideSharedPreferencesInstance(application: Application): SharedPreferences {
        return SharedPreferences(application)
    }


//    @Provides
//    fun provideResources(application: Application): Resources {
//        return application.resources
//    }

}