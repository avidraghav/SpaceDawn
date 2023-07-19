package com.raghav.spacedawnv2.util

import android.content.BroadcastReceiver
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class Helpers {
    companion object {
        fun String.toDate(
            dateFormat: String,
            timeZone: TimeZone = TimeZone.getTimeZone("UTC")
        ): Date {
            val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
            parser.timeZone = timeZone
            return parser.parse(this)
        }

        fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
            val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
            formatter.timeZone = timeZone
            return formatter.format(this)
        }

        fun NavHostController.navigateSingleTopTo(route: String) =
            this.navigate(route) {
                popUpTo(
                    this@navigateSingleTopTo.graph.findStartDestination().id
                ) {
                    inclusive = true
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
                launchSingleTop = true
            }

        fun String?.isNull(): Boolean {
            return this == null
        }

        /**
         * extension function to be used in BroadCastReceivers to delegate long
         * running tasks to background thread. Uses the goAsync() function which makes the BroadcastCastReceiver
         * running for more than 30 seconds so as to complete the work. If the work is completed
         * before the limit the BroadcastCastReceiver is cancelled.
         *
         * @param dispatcher CoroutineDispatcher
         * @param block work that needs to be done on the background thread
         */
        fun BroadcastReceiver.doBackgroundWork(
            dispatcher: CoroutineDispatcher = Dispatchers.IO,
            block: suspend CoroutineScope.() -> Unit
        ) {
            val pendingResult = goAsync()
            CoroutineScope(dispatcher + SupervisorJob()).launch {
                try {
                    block()
                } finally {
                    pendingResult.finish()
                }
            }
        }
    }
}
