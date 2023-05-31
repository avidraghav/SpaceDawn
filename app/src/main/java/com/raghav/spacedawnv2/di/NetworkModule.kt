package com.raghav.spacedawnv2.di

import BaseUrlProvider
import com.raghav.spacedawnv2.data.remote.LaunchesApi
import com.raghav.spacedawnv2.data.repository.LaunchesRepositoryImpl
import com.raghav.spacedawnv2.domain.repository.LaunchesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLaunchesApi(): LaunchesApi {
        return Retrofit.Builder()
            .baseUrl(BaseUrlProvider.BASE_URL_LAUNCHES_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(LaunchesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLaunchesRepository(launchesApi: LaunchesApi): LaunchesRepository {
        return LaunchesRepositoryImpl(launchesApi)
    }
}
