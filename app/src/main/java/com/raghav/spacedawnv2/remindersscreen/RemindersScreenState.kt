package com.raghav.spacedawnv2.remindersscreen

import com.raghav.spacedawnv2.domain.model.Reminder

data class RemindersScreenState(
    val reminders: List<Reminder> = emptyList(),
    val isLoading: Boolean = false,
    val infoMessage: String? = null
)
