package com.raghav.spacedawnv2.domain.usecase

import com.raghav.spacedawnv2.domain.repository.RemindersRepository
import com.raghav.spacedawnv2.domain.util.ReminderScheduler
import com.raghav.spacedawnv2.domain.util.Resource
import javax.inject.Inject

/**
 * A UseCase contains a comprehensive list of steps needed to perform by a
 * single task, by a single actor. (SOLID's Single Responsibility principle)
 * For eg. If a UseCase saves a user profile in database
 * then the UseCase should be written in a way such that one doesn't has
 * to do anything extra to save the profile as well as there is no sense to execute only a subset of the
 * steps performed by the UseCase
 */

/**
 * Fetches Upcoming Launches
 * @param repository [RemindersRepository]
 *
 * @return Resource<List<LaunchDetail>
 *
 * @see Resource
 */
class CancelReminderUseCase @Inject constructor(
    private val repository: RemindersRepository,
    private val reminderScheduler: ReminderScheduler
) {
    suspend operator fun invoke(id: String): Resource<Nothing?> {
        return try {
            reminderScheduler.cancelReminder(id)
            repository.deleteReminderFromDb(id)
            Resource.Success(null)
        } catch (e: Exception) {
            Resource.Error(message = e.localizedMessage)
        }
    }
}
