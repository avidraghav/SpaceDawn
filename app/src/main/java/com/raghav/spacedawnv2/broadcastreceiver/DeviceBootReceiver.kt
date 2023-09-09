package com.raghav.spacedawnv2.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.raghav.spacedawnv2.worker.DeviceBootReminderWorker
import com.raghav.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Receives the broadcast of the device being rebooted.
 *
 * Uses WorkManager to reset the Launch Reminders
 * as reminders set via AlarmManager are cancelled when the
 * device is rebooted.
 */
@AndroidEntryPoint
class DeviceBootReceiver : BroadcastReceiver() {

    @Inject
    lateinit var workManager: WorkManager

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
