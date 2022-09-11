package com.bronski.android.scidtesttask.di.modules

import android.content.Context
import androidx.room.Room
import com.bronski.android.scidtesttask.core.data.room.AppDatabase
import com.bronski.android.scidtesttask.core.data.room.DataDao
import com.bronski.android.scidtesttask.core.utils.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context, AppDatabase::class.java, DB_NAME
        ).build()

    @Provides
    @Singleton
    fun provideLaunchesDao(database: AppDatabase): DataDao =
        database.dataDao()

}