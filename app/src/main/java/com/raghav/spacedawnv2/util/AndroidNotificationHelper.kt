package com.raghav.spacedawnv2.util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.raghav.spacedawnv2.R
import com.raghav.spacedawnv2.domain.util.NotificationHelper
import com.raghav.spacedawnv2.ui.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Utility class for sending app notifications
 */
@Singleton
class AndroidNotificationHelper @Inject constructor(
    @ApplicationContext val context: Context?
) : NotificationHelper {

    @SuppressLint("NewApi")
    override fun createNotificationChannel(
        name: String,
        id: String,
        importance: Int,
        vibratePattern: LongArray?
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
        val manager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    override fun showNotification(
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
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        }

        val notificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(notificationId, builder?.build())
    }
}
