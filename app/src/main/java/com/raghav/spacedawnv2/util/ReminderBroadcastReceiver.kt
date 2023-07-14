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
import androidx.core.app.NotificationCompat
import com.raghav.spacedawnv2.R
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
        val msg = p1?.getStringExtra("key") ?: return
        // mediaPlayer.isLooping = true
        // mediaPlayer.start()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(
                context,
                REMINDER_NOTIFICATION_CHANNEL,
                REMINDER_CHANNEL_ID,
                NotificationManager.IMPORTANCE_HIGH
            )
        }

        showNotification(context, REMINDER_CHANNEL_ID, 1, msg, "")
    }

    @SuppressLint("NewApi")
    private fun createNotificationChannel(
        context: Context?,
        name: String,
        id: String,
        importance: Int,
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

    fun showNotification(
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
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }

        val notificationManager =
            context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.areNotificationsEnabled()
        notificationManager.notify(notificationId, builder?.build())
    }
}
