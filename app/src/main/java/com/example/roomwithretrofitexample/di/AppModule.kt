package com.example.roomwithretrofitexample.di

import android.content.Context
import androidx.room.Room
import com.example.roomwithretrofitexample.data.local.AppDao
import com.example.roomwithretrofitexample.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @[Provides Singleton]
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "testCrypto.db"
        ).build()

    @Provides
    fun provideAppDao(appDatabase: AppDatabase): AppDao = appDatabase.userDao()

}