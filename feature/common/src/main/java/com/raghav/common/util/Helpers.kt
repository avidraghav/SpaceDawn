package com.raghav.common.util

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings

/**
 * Extension functions which are android framework dependent and
 * can be used by any feature inside the feature module.
 */
class Helpers {
    companion object {
        fun Activity.openAppSettings() {
            val intent = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", this.packageName, null)
            )
            this.startActivity(intent)
        }
    }
}
