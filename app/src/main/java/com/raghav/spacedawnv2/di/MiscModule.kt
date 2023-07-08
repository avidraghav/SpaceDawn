package com.raghav.spacedawnv2.di

import android.content.Context
import com.raghav.spacedawnv2.domain.util.ReminderScheduler
import com.raghav.spacedawnv2.util.AndroidReminderScheduler
import com.raghav.spacedawnv2.util.CoroutineDispatchers
import com.raghav.spacedawnv2.util.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MiscModule {

    @Provides
    @Singleton
    fun provideReminderScheduler(@ApplicationContext context: Context): ReminderScheduler {
        return AndroidReminderScheduler(context)
    }

    @Singleton
    @Provides
    fun provideDispatchers(): DispatchersProvider = CoroutineDispatchers()
}
