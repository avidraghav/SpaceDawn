package com.raghav.spacedawnv2.data.repository

import com.raghav.spacedawnv2.data.local.RemindersDao
import com.raghav.spacedawnv2.domain.model.Reminder
import com.raghav.spacedawnv2.domain.repository.RemindersRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class RemindersRepositoryImpl @Inject constructor(
    private val remindersDao: RemindersDao
) : RemindersRepository {
    override fun getRemindersFromDb(): Flow<List<Reminder>> {
        return remindersDao.getReminders()
    }

    override suspend fun deleteReminderFromDb(reminderId: String) {
        remindersDao.deleteReminder(reminderId)
    }
}
