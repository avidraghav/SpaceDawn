package com.raghav.spacedawnv2.di

import com.raghav.spacedawnv2.domain.util.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReleaseInterceptorModule {

    @Singleton
    @Provides
    fun provideInterceptor(): NetworkInterceptor =
        NoOpInterceptor()
}
