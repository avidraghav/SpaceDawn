package com.raghav.spacedawnv2.di

import com.raghav.spacedawnv2.util.DefaultDispatchers
import com.raghav.spacedawnv2.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Singleton
    @Provides
    fun provideCoroutineDispatchers(): DispatcherProvider = DefaultDispatchers()
}
