package com.raghav.spacedawnv2.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.util.ReminderPermissionState
import com.raghav.spacedawnv2.domain.util.ReminderScheduler
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Schedules Reminders/Alarms by using Android's
 * [AlarmManager](https://developer.android.com/reference/android/app/AlarmManager)
 * also see [Simplifying AlarmManager: Understanding Alarm Scheduling in Android](https://avidraghav.hashnode.dev/simplifying-alarmmanager-understanding-alarm-scheduling-in-android)
 * @param context Application Context
 */
class AndroidReminderScheduler @Inject constructor(
    @ApplicationContext private val context: Context
) : ReminderScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun setReminder(launchDetail: LaunchDetail): ReminderPermissionState {
        // Apps targeting Android version 12 (Version Code - S, Api Level 31)
        // or higher need to request for SCHEDULE_EXACT_ALARM permission explicitly whereas
        // Apps targeting any Android version below 12 don't need to do ask for this permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager.canScheduleExactAlarms()) {
                setAlarm(launchDetail)
                return ReminderPermissionState.SetSuccessfully
            } else {
                return ReminderPermissionState.PermissionNotAvailable
            }
        } else {
            setAlarm(launchDetail)
            return ReminderPermissionState.SetSuccessfully
        }
    }

    override fun cancelReminder(id: String) {
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            id.hashCode(),
            Intent(context, ReminderBroadcastReceiver::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(
            pendingIntent
        )
    }

    private fun setAlarm(launchDetail: LaunchDetail) {
        val intent = Intent(context, ReminderBroadcastReceiver::class.java).apply {
            putExtra("key", launchDetail.name)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            launchDetail.id.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + 10_000,
            pendingIntent
        )
    }
}
