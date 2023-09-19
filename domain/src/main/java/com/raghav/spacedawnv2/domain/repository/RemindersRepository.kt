package com.raghav.spacedawnv2.domain.repository

import com.raghav.spacedawnv2.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

interface RemindersRepository {

    fun getRemindersFromDb(): Flow<List<Reminder>>
    suspend fun deleteReminderFromDb(reminderId: String)
}
