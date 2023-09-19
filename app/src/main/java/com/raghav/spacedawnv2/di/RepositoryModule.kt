package com.raghav.spacedawnv2.di

import com.raghav.spacedawnv2.data.local.LaunchesDao
import com.raghav.spacedawnv2.data.local.RemindersDao
import com.raghav.spacedawnv2.data.remote.LaunchesApi
import com.raghav.spacedawnv2.data.repository.LaunchesRepositoryImpl
import com.raghav.spacedawnv2.data.repository.RemindersRepositoryImpl
import com.raghav.spacedawnv2.data.util.NetworkConnectivityManager
import com.raghav.spacedawnv2.domain.repository.LaunchesRepository
import com.raghav.spacedawnv2.domain.repository.RemindersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideLaunchesRepository(
        launchesApi: LaunchesApi,
        launchesDao: LaunchesDao,
        connectivityManager: NetworkConnectivityManager
    ): LaunchesRepository {
        return LaunchesRepositoryImpl(launchesApi, launchesDao, connectivityManager)
    }

    @Singleton
    @Provides
    fun provideRemindersRepository(
        remindersDao: RemindersDao
    ): RemindersRepository {
        return RemindersRepositoryImpl(remindersDao)
    }
}
