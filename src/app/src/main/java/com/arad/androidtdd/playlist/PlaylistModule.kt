package com.arad.androidtdd.playlist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {

    @Provides
    fun playlistsAPI(retrofit: Retrofit): PlayListAPI = retrofit.create(PlayListAPI::class.java)
    
    @Provides
    fun retrofit(): Retrofit =Retrofit.Builder()
            .baseUrl("http://192.168.1.2:3000/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}