package com.kseniabl.data.main.di

import com.kseniabl.data.main.source.BannersSource
import com.kseniabl.data.main.source.LocalSource
import com.kseniabl.data.main.source.RetrofitRequests
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BeerSourceModule {

    @Provides
    fun provideBaseUrl(): String = "https://api.punkapi.com/v2/"

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String) : RetrofitRequests = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(RetrofitRequests::class.java)

    @Provides
    @Singleton
    fun provideLocalSource(): LocalSource =
        BannersSource()
}