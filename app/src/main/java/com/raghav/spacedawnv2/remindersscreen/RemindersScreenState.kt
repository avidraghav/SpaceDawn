package com.raghav.spacedawnv2.remindersscreen

import androidx.compose.runtime.Stable
import com.raghav.spacedawnv2.domain.model.Reminder
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class RemindersScreenState(
    val reminders: ImmutableList<Reminder> = persistentListOf(),
    val isLoading: Boolean = false,
    val infoMessage: String? = null
)
