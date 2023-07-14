package com.raghav.spacedawnv2.util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.CountDownTimer
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC
import com.raghav.spacedawnv2.R
import com.raghav.spacedawnv2.domain.util.Constants
import com.raghav.spacedawnv2.domain.util.Constants.REMINDER_CHANNEL_ID
import com.raghav.spacedawnv2.domain.util.Constants.REMINDER_NOTIFICATION_CHANNEL
import com.raghav.spacedawnv2.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReminderBroadcastReceiver : BroadcastReceiver() {

    @Inject
    lateinit var mediaPlayer: MediaPlayer

    override fun onReceive(context: Context?, p1: Intent?) {
        val launchName = p1?.getStringExtra(Constants.KEY_LAUNCH_NAME) ?: return
        mediaPlayer.start()

        val countDownTimer = object : CountDownTimer(Constants.REMINDER_SOUND_DURATION, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                mediaPlayer.stop()
            }
        }
        countDownTimer.start()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(
                context = context,
                name = REMINDER_NOTIFICATION_CHANNEL,
                id = REMINDER_CHANNEL_ID,
                importance = NotificationManager.IMPORTANCE_HIGH
            )
        }

        showNotification(
            context = context,
            channelId = REMINDER_CHANNEL_ID,
            notificationId = 1,
            title = "Reminder From Space Dawn",
            content = "$launchName mission is about to launch"
        )
    }

    @SuppressLint("NewApi")
    private fun createNotificationChannel(
        context: Context?,
        name: String,
        id: String,
        importance: Int,
        // eg: longArrayOf(0, 400, 200, 400)
        // Vibration pattern (0ms delay, vibrate for 400ms, wait for 200ms, vibrate for 400ms)
        vibratePattern: LongArray? = null
    ) {
        val channel = NotificationChannel(
            id,
            name,
            importance
        )
        vibratePattern?.let {
            channel.enableVibration(true)
            channel.vibrationPattern = vibratePattern
        }
        val manager = context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    private fun showNotification(
        context: Context?,
        channelId: String,
        notificationId: Int,
        title: String,
        content: String
    ) {
        val notificationIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val builder = context?.let {
            NotificationCompat.Builder(it, channelId)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setVisibility(VISIBILITY_PUBLIC)
        }

        val notificationManager =
            context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(notificationId, builder?.build())
    }
}
