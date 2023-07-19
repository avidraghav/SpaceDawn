package com.raghav.spacedawnv2.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.raghav.spacedawnv2.data.local.LaunchesDao
import com.raghav.spacedawnv2.domain.util.ReminderScheduler
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

/**
 * Worker which resets the reminder after device is booted up.
 */
@HiltWorker
class DeviceBootReminderWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val androidReminderScheduler: ReminderScheduler,
    private val dao: LaunchesDao
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return try {
            dao.getReminders().collect {
                it.forEach { reminder ->
                    androidReminderScheduler.setReminder(reminder)
                }
            }
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }
}
