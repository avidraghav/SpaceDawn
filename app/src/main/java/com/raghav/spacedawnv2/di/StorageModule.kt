package com.raghav.spacedawnv2.di

import android.content.Context
import androidx.room.Room
import com.raghav.spacedawnv2.data.local.LaunchesDatabase
import com.raghav.spacedawnv2.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {
    @Singleton
    @Provides
    fun provideLaunchesDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        LaunchesDatabase::class.java,
        Constants.LAUNCHES_DB
    ).build()

    @Singleton
    @Provides
    fun provideReposDao(database: LaunchesDatabase) = database.getLaunchesDao()
}
