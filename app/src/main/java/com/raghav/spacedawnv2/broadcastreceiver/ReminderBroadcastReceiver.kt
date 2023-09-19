package com.raghav.spacedawnv2.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.raghav.spacedawnv2.domain.util.Constants
import com.raghav.spacedawnv2.worker.ReminderNotificationCleanupWorker
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Receives the Broadcast when a Launch Reminder is fired off by the device
 *
 * Uses WorkManager to Show the notification to user and then delete that
 * reminder from app's local database.
 */
@AndroidEntryPoint
class ReminderBroadcastReceiver : BroadcastReceiver() {

    @Inject
    lateinit var workManager: WorkManager

    override fun onReceive(context: Context?, p1: Intent?) {
        val launchName = p1?.getStringExtra(Constants.KEY_LAUNCH_NAME)
        val launchId = p1?.getStringExtra(Constants.KEY_LAUNCH_ID)

        val workRequest = OneTimeWorkRequestBuilder<ReminderNotificationCleanupWorker>()
            .setInputData(
                workDataOf(
                    Constants.KEY_LAUNCH_NAME to launchName,
                    Constants.KEY_LAUNCH_ID to launchId
                )
            )
            .build()
        workManager.enqueueUniqueWork(
            Constants.REMINDER_NOTIFICATION_AND_CLEANUP,
            ExistingWorkPolicy.APPEND_OR_REPLACE,
            workRequest
        )
    }
}
