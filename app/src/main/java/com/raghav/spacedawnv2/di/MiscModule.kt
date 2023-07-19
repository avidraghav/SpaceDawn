package com.raghav.spacedawnv2.di

import android.content.Context
import android.media.MediaPlayer
import android.provider.Settings
import androidx.work.WorkManager
import com.raghav.spacedawnv2.domain.util.NotificationHelper
import com.raghav.spacedawnv2.domain.util.ReminderScheduler
import com.raghav.spacedawnv2.util.AndroidNotificationHelper
import com.raghav.spacedawnv2.util.AndroidReminderScheduler
import com.raghav.spacedawnv2.util.CoroutineDispatchers
import com.raghav.spacedawnv2.util.DispatchersProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MiscModule {

    companion object {
        @Provides
        @Singleton
        fun provideReminderScheduler(@ApplicationContext context: Context): ReminderScheduler {
            return AndroidReminderScheduler(context)
        }

        @Singleton
        @Provides
        fun provideDispatchers(): DispatchersProvider = CoroutineDispatchers()

        @Provides
        fun provideMediaPlayer(@ApplicationContext context: Context): MediaPlayer =
            MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI)

        @Singleton
        @Provides
        fun provideWorkManager(@ApplicationContext context: Context): WorkManager =
            WorkManager.getInstance(context)
    }

    @Binds
    abstract fun bindNotificationHelper(notificationHelper: AndroidNotificationHelper): NotificationHelper
}
