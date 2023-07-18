package com.raghav.spacedawnv2.domain.repository

import com.raghav.spacedawnv2.domain.model.LaunchDetail
import kotlinx.coroutines.flow.Flow

interface RemindersRepository {

    fun getRemindersFromDb(): Flow<List<LaunchDetail>>
    suspend fun deleteReminderFromDb(reminderId: String)
}
