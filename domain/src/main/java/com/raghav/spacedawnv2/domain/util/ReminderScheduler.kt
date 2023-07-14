package com.raghav.spacedawnv2.domain.util

import com.raghav.spacedawnv2.domain.model.LaunchDetail

/**
 * Implement this Interface to write platform specific
 * implementation for setting and cancelling reminders
 */
interface ReminderScheduler {

    /**
     * Set a reminder for a launch and save that launch in
     * local database
     */
    fun setReminder(launchDetail: LaunchDetail): ReminderState

    /**
     * Cancel an already set reminder
     */
    fun cancelReminder(id: String)
}

sealed class ReminderState {

    data class PermissionsState(
        val reminderPermission: Boolean,
        val notificationPermission: Boolean
    ) : ReminderState()

    object SetSuccessfully : ReminderState()
}
