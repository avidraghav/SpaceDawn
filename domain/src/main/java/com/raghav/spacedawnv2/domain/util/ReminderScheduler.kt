package com.raghav.spacedawnv2.domain.util

import com.raghav.spacedawnv2.domain.model.Reminder

/**
 * Implement this Interface to write platform specific
 * implementation for setting and cancelling reminders
 */
interface ReminderScheduler {

    /**
     * Set a reminder for a launch and save that launch in
     * local database.
     * @param reminder object of [Reminder]
     */
    fun setReminder(reminder: Reminder): ReminderState

    /**
     * Cancel an already set reminder
     * @param id unique id associated with the saved reminder
     */
    fun cancelReminder(id: String)
}

sealed class ReminderState(val errorMessage: String? = null) {

    data class PermissionsState(
        val reminderPermission: Boolean,
        val notificationPermission: Boolean
    ) : ReminderState()

    object SetSuccessfully : ReminderState()
    data class NotSet(val message: String) : ReminderState(message)
}
