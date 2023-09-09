package com.raghav.launches.util

import com.raghav.spacedawnv2.domain.model.Reminder
import com.raghav.spacedawnv2.domain.util.ReminderScheduler
import com.raghav.spacedawnv2.domain.util.ReminderState

class FakeReminderScheduler : ReminderScheduler {

    override fun setReminder(reminder: Reminder): ReminderState {
        return ReminderState.SetSuccessfully
    }

    override fun cancelReminder(id: String) {
    }
}
