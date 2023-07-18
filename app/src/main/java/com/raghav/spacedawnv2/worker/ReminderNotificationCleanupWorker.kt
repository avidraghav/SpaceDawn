package com.raghav.spacedawnv2.worker

import android.app.NotificationManager
import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.raghav.spacedawnv2.data.local.LaunchesDao
import com.raghav.spacedawnv2.domain.util.Constants
import com.raghav.spacedawnv2.domain.util.NotificationHelper
import com.raghav.spacedawnv2.util.DispatchersProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlin.random.Random
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * Worker which plays the media sound, displays the reminder notification and deletes the
 * saved reminder afterwards.
 */
@HiltWorker
class ReminderNotificationCleanupWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val mediaPlayer: MediaPlayer,
    private val notificationHelper: NotificationHelper,
    private val dispatchers: DispatchersProvider,
    private val dao: LaunchesDao
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return try {
            val launchName = inputData.getString(Constants.KEY_LAUNCH_NAME)
            val launchId = inputData.getString(Constants.KEY_LAUNCH_ID)

            withContext(dispatchers.main) {
                mediaPlayer.start()
                showNotification(launchName)
                delay(Constants.REMINDER_SOUND_DURATION)
                mediaPlayer.stop()
            }
            launchId?.let { dao.deleteReminder(it) }
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            withContext(dispatchers.main) {
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
