package com.raghav.spacedawnv2.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.raghav.spacedawnv2.data.local.LaunchesDao
import com.raghav.spacedawnv2.domain.util.Constants
import com.raghav.spacedawnv2.domain.util.ReminderScheduler
import com.raghav.spacedawnv2.worker.DeviceBootReminderWorker
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DeviceBootReceiver : BroadcastReceiver() {

    @Inject
    lateinit var workManager: WorkManager

    @Inject
    lateinit var androidReminderScheduler: ReminderScheduler

    @Inject
    lateinit var launchesDao: LaunchesDao

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            val workRequest = OneTimeWorkRequestBuilder<DeviceBootReminderWorker>()
                .build()
            workManager.enqueueUniqueWork(
                Constants.SET_REMINDER_AFTER_DEVICE_BOOT,
                ExistingWorkPolicy.REPLACE,
                workRequest
            )
        }
    }
}
