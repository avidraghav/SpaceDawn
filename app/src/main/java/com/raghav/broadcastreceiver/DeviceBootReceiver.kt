package com.raghav.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.raghav.spacedawnv2.data.local.LaunchesDao
import com.raghav.spacedawnv2.domain.util.ReminderScheduler
import com.raghav.spacedawnv2.util.Helpers.Companion.goAsync
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

@AndroidEntryPoint
class DeviceBootReceiver : BroadcastReceiver() {

    @Inject
    lateinit var reminderScheduler: ReminderScheduler

    @Inject
    lateinit var dao: LaunchesDao

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            goAsync(Dispatchers.IO) {
                dao.getReminders().collect {
                    it.forEach { reminder ->
                        reminderScheduler.setReminder(reminder)
                    }
                }
            }
        }
    }
}
