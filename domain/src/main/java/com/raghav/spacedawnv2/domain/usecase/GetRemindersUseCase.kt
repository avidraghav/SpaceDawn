package com.raghav.spacedawnv2.domain.usecase

import com.raghav.spacedawnv2.domain.model.Reminder
import com.raghav.spacedawnv2.domain.repository.RemindersRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

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
 * @return Flow<LaunchDetail>
 */
class GetRemindersUseCase @Inject constructor(
    private val repository: RemindersRepository
) {
    operator fun invoke(): Flow<List<Reminder>> {
        return repository.getRemindersFromDb()
    }
}
