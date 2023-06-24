package com.raghav.spacedawnv2.domain.util

import com.raghav.spacedawnv2.domain.model.LaunchDetail

interface ReminderScheduler {

    fun setReminder(launchDetail: LaunchDetail)
    fun cancelReminder(id: String)
}
