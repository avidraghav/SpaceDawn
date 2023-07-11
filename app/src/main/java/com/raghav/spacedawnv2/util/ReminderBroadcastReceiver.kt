package com.raghav.spacedawnv2.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ReminderBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        val msg = p1?.getStringExtra("key") ?: return
        // Notification implementation pending
        println("alarm triggered: $msg")
    }
}
