package com.raghav.broadcastreceiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.CountDownTimer
import com.raghav.spacedawnv2.domain.util.Constants
import com.raghav.spacedawnv2.domain.util.Constants.REMINDER_CHANNEL_ID
import com.raghav.spacedawnv2.domain.util.Constants.REMINDER_NOTIFICATION_CHANNEL
import com.raghav.spacedawnv2.util.AndroidNotificationHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReminderBroadcastReceiver : BroadcastReceiver() {

    @Inject
    lateinit var mediaPlayer: MediaPlayer

    @Inject
    lateinit var notificationHelper: AndroidNotificationHelper

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
            notificationHelper.createNotificationChannel(
                name = REMINDER_NOTIFICATION_CHANNEL,
                id = REMINDER_CHANNEL_ID,
                importance = NotificationManager.IMPORTANCE_HIGH
            )
        }

        notificationHelper.showNotification(
            channelId = REMINDER_CHANNEL_ID,
            notificationId = 1,
            title = "Reminder From Space Dawn",
            content = "$launchName mission is about to launch"
        )
    }
}
