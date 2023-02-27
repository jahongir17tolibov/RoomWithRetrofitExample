package com.example.roomwithretrofitexample.di

import com.example.roomwithretrofitexample.networking.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @[Provides Singleton]
    fun provideJsonGsonConvertor(): GsonConverterFactory = GsonConverterFactory.create()

    @[Provides Singleton]
    fun provideCryptoRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.coinlore.net/api/")
        .addConverterFactory(provideJsonGsonConvertor())
        .build()

}