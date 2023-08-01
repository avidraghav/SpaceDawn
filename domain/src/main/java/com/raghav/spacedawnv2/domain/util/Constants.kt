package com.raghav.spacedawnv2.domain.util

object Constants {
    const val LAUNCH_DATE_INPUT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val DATE_OUTPUT_FORMAT = "MMMM dd,yyyy HH:mm z"
    const val LAUNCHES_DB = "launches_db.db"
    const val REMINDER_NOT_SET = "Reminder Couldn't be Set"
    const val REMINDER_SET = "Reminder Set Successfully"
    const val REMINDER_NOTIFICATION_CHANNEL = "Launch Reminders"
    const val REMINDER_CHANNEL_ID = "launch_reminders"
    const val REMINDER_PERMISSION_NOT_AVAILABLE = "reminder_permission_not_available"
    const val NOTIFICATION_PERMISSION_NOT_AVAILABLE = "notification_permission_not_available"
    const val NOTIFICATION_REMINDER_PERMISSION_NOT_AVAILABLE =
        "notification_reminder_permission_not_available"
    const val KEY_LAUNCH_NAME = "key_launch_name"
    const val KEY_LAUNCH_ID = "key_reminder_name"
    const val TEN_MINUTES_IN_MILLIS = 600_000L
    const val REMINDER_SOUND_DURATION = 10_000L
    const val REMINDER_NOTIFICATION_AND_CLEANUP = "reminder_notification_and_cleanup"
    const val SET_REMINDER_AFTER_DEVICE_BOOT = "set_reminder_after_device_boot"
    const val API_THROTTLED_MESSAGE = "Requests Limit Reached, please try after 1 hour"
    const val UNKNOWN_ERROR_MESSAGE = "Some Unknown Error Occurred"
}
