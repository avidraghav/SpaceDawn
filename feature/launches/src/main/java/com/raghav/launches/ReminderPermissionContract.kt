package com.raghav.launches

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContract

class ReminderPermissionContract : ActivityResultContract<String, Boolean>() {
    @SuppressLint("InlinedApi")
    override fun createIntent(context: Context, input: String): Intent {
        return Intent(
            Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM,
            Uri.fromParts("package", (context as Activity).packageName, null)
        )
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
        return resultCode == ComponentActivity.RESULT_OK
    }
}
