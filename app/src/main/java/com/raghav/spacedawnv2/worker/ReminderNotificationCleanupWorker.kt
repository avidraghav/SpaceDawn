package com.raghav.spacedawnv2.worker

import android.app.NotificationManager
import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.raghav.spacedawnv2.data.local.RemindersDao
import com.raghav.spacedawnv2.domain.util.Constants
import com.raghav.spacedawnv2.domain.util.NotificationHelper
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random

/**
 * Worker which plays the media sound, displays the reminder notification and deletes the
 * saved reminder from app's local database afterwards.
 */
@HiltWorker
class ReminderNotificationCleanupWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val mediaPlayer: MediaPlayer,
    private val notificationHelper: NotificationHelper,
    private val remindersDao: RemindersDao
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return try {
            val launchName = inputData.getString(Constants.KEY_LAUNCH_NAME)
            val launchId = inputData.getString(Constants.KEY_LAUNCH_ID)

            withContext(Dispatchers.Main) {
                mediaPlayer.start()
                showNotification(launchName)
                delay(Constants.REMINDER_SOUND_DURATION)
                mediaPlayer.stop()
            }
            launchId?.let { remindersDao.deleteReminder(it) }
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            withContext(Dispatchers.Main) {
                mediaPlayer.stop()
            }
            Result.failure()
        }
    }

    private fun showNotification(launchName: String?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationHelper.createNotificationChannel(
                name = Constants.REMINDER_NOTIFICATION_CHANNEL,
                id = Constants.REMINDER_CHANNEL_ID,
                importance = NotificationManager.IMPORTANCE_HIGH
            )
        }

        notificationHelper.showNotification(
            channelId = Constants.REMINDER_CHANNEL_ID,
            notificationId = Random.nextInt(),
            title = "Reminder From Space Dawn",
            content = "$launchName mission is about to launch"
        )
    }
}
