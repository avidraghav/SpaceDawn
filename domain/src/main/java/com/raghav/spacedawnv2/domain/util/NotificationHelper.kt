package com.raghav.spacedawnv2.domain.util

/**
 * Implement this Interface to write platform specific
 * implementation for sending notifications to the user
 */
interface NotificationHelper {

    /**
     * Creates a notification channel. Needed only when the Android version of the device is 8.0 or greater
     * @param id The id of the channel. Must be unique per package. The value may be truncated if it is too long.
     * @param name The user visible name of the channel. You can rename this channel when the system locale changes by listening for the Intent.ACTION_LOCALE_CHANGED broadcast.
     * The recommended maximum length is 40 characters; the value may be truncated if it is too long.
     * @param importance The importance of the channel. This controls how interruptive notifications posted to this channel are.
     * @param vibratePattern Sets the vibration pattern for notifications posted to this channel.
     * If the provided pattern is valid (non-null, non-empty), will enableVibration(boolean) enable vibration} as well.
     * Otherwise, vibration will be disabled.
     * Only modifiable before the channel is submitted to NotificationManager.createNotificationChannel(NotificationChannel).
     *
     *  eg: longArrayOf(0, 400, 200, 400) which corresponds to
     *  (0ms delay, vibrate for 400ms, wait for 200ms, vibrate for 400ms)
     */
    fun createNotificationChannel(
        name: String,
        id: String,
        importance: Int,
        vibratePattern: LongArray? = null
    )

    /**
     * @param channelId The constructed Notification will be posted on this NotificationChannel.
     * @param notificationId An identifier for this notification unique within your application.
     * @param title Set the title (first row) of the notification.
     * @param content Set the text (second row) of the notification.
     */

    fun showNotification(
        channelId: String,
        notificationId: Int,
        title: String,
        content: String
    )
}
