package com.raghav.spacedawnv2.di

import com.raghav.spacedawnv2.domain.repository.LaunchesRepository
import com.raghav.spacedawnv2.domain.usecase.GetLaunchesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetLaunchesUseCase(repository: LaunchesRepository) = GetLaunchesUseCase(repository)
}
