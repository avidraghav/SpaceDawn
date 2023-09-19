package com.raghav.spacedawnv2.util

import com.raghav.spacedawnv2.data.remote.dto.LaunchesResponseDto
import com.raghav.spacedawnv2.data.remote.dto.toDomain
import com.raghav.spacedawnv2.domain.model.Reminder
import com.raghav.spacedawnv2.domain.model.toReminder
import com.raghav.spacedawnv2.domain.repository.RemindersRepository
import com.raghav.spacedawnv2.util.Helpers.Companion.getDtoFromJson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FakeRemindersRepository(
    private val remindersFetchedSuccessfully: Boolean = true,
    private val reminderDeletedSuccessfully: Boolean = true
) :
    RemindersRepository {

    override fun getRemindersFromDb(): Flow<List<Reminder>> {
        return if (remindersFetchedSuccessfully) {
            val launchesResponse =
                getDtoFromJson<LaunchesResponseDto>(launchesResponseDtoString)?.toDomain()

            val reminders =
                launchesResponse?.results?.filterNotNull()?.map {
                    it.toReminder()
                } ?: emptyList()

            flowOf(reminders)
        } else {
            flow {
                throw Throwable(message = "Some Error Occurred")
            }
        }
    }

    override suspend fun deleteReminderFromDb(reminderId: String) {
        if (reminderDeletedSuccessfully.not()) {
            throw Exception("Some Error Occurred")
        }
    }
}
