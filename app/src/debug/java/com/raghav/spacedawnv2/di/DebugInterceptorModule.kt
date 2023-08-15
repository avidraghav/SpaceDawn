package com.raghav.spacedawnv2.di

import android.content.Context
import com.raghav.spacedawnv2.domain.util.NetworkInterceptor
import com.raghav.spacedawnv2.util.FakeInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DebugInterceptorModule {

    @Singleton
    @Provides
    fun provideInterceptor(@ApplicationContext context: Context): NetworkInterceptor =
        FakeInterceptor(context)
}
