package com.raghav.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Extension functions which can be used throughout the project
 * and which don't depend on the Android Framework
 */
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

        fun String?.isNull(): Boolean {
            return this == null
        }
    }
}
