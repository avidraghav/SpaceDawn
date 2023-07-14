package com.raghav.spacedawnv2.domain.usecase

import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.repository.LaunchesRepository
import com.raghav.spacedawnv2.domain.util.Constants
import com.raghav.spacedawnv2.domain.util.ReminderScheduler
import com.raghav.spacedawnv2.domain.util.ReminderState
import com.raghav.spacedawnv2.domain.util.Resource
import javax.inject.Inject

/**
 * A UseCase contains a comprehensive list of steps needed to perform by a
 * single task, by a single actor. (SOLID's Single Responsibility principle)
 * For eg. If a UseCase saves a user profile in database
 * then the UseCase should be written in a way such that one doesn't has
 * to do anything extra to save the profile as well as there is no sense to execute only a subset of the
 * steps performed by the UseCase
 *
 */

/**
 * Creates a reminder/alarm for the specified launch
 * and saves that launch in local database
 * @param repository [LaunchesRepository]
 * @param reminderScheduler Platform specific Implementation of [ReminderScheduler]
 *
 * @return Resource<Nothing?>
 *
 * @see Resource
 */

class AddReminderUseCase @Inject constructor(
    private val repository: LaunchesRepository,
    private val reminderScheduler: ReminderScheduler
) {
    suspend operator fun invoke(launchDetail: LaunchDetail): Resource<Nothing?> {
        return try {
            val result = reminderScheduler.setReminder(launchDetail)
            return when (result) {
                ReminderState.SetSuccessfully -> {
                    repository.saveReminderInDb(launchDetail)
                    Resource.Success(null)
                }

                is ReminderState.NotSet -> {
                    Resource.Error(message = result.errorMessage!!)
                }

                is ReminderState.PermissionsState -> {
                    when {
                        result.reminderPermission && !result.notificationPermission -> {
                            Resource.Error(message = Constants.NOTIFICATION_PERMISSION_NOT_AVAILABLE)
                        }

                        !result.reminderPermission && result.notificationPermission -> {
                            Resource.Error(message = Constants.REMINDER_PERMISSION_NOT_AVAILABLE)
                        }

                        !result.reminderPermission && !result.notificationPermission -> {
                            Resource.Error(message = Constants.NOTIFICATION_REMINDER_PERMISSION_NOT_AVAILABLE)
                        }

                        else -> {
                            Resource.Error(message = Constants.NOTIFICATION_REMINDER_PERMISSION_NOT_AVAILABLE)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Resource.Error(message = e.localizedMessage)
        }
    }
}
