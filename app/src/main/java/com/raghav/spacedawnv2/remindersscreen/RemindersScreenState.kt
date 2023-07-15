package com.raghav.spacedawnv2.remindersscreen

import com.raghav.spacedawnv2.domain.model.LaunchDetail

data class RemindersScreenState(
    val reminders: List<LaunchDetail> = emptyList(),
    val isLoading: Boolean = false,
    val infoMessage: String? = null
)
