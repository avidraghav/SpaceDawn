package com.raghav.spacedawnv2.util

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

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
    }
}
