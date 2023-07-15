package com.raghav.spacedawnv2.data.repository

import com.raghav.spacedawnv2.data.local.LaunchesDao
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.repository.RemindersRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class RemindersRepositoryImpl @Inject constructor(
    private val launchesDao: LaunchesDao
) : RemindersRepository {
    override fun getRemindersFromDb(): Flow<List<LaunchDetail>> {
        return launchesDao.getReminders()
    }

    override suspend fun deleteReminderFromDb(launchDetail: LaunchDetail) {
        launchesDao.deleteReminder(launchDetail)
    }
}
