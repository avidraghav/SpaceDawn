package com.raghav.spacedawnv2.data.di

import android.content.Context
import androidx.room.Room
import com.raghav.spacedawnv2.data.local.Convertors
import com.raghav.spacedawnv2.data.local.SpaceDawnDatabase
import com.raghav.util.Constants
import com.squareup.moshi.Moshi
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
        @ApplicationContext context: Context,
        moshi: Moshi
    ): SpaceDawnDatabase {
        val typeConverter = Convertors(moshi)
        return Room.databaseBuilder(
            context,
            SpaceDawnDatabase::class.java,
            Constants.SPACE_DAWN_DB
        ).addTypeConverter(typeConverter).build()
    }

    @Singleton
    @Provides
    fun provideLaunchesDao(database: SpaceDawnDatabase) = database.getLaunchesDao()

    @Singleton
    @Provides
    fun provideRemindersDao(database: SpaceDawnDatabase) = database.getRemindersDao()
}
