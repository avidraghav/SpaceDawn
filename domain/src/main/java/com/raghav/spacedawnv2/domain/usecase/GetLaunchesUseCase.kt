package com.raghav.spacedawnv2.domain.usecase

import com.raghav.spacedawnv2.domain.model.LaunchesResponse
import com.raghav.spacedawnv2.domain.repository.LaunchesRepository
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
class GetLaunchesUseCase @Inject constructor(
    private val repository: LaunchesRepository
) {
    suspend operator fun invoke(): Resource<LaunchesResponse> {
        return try {
            Resource.Success(repository.getLaunches())
        } catch (e: Exception) {
            Resource.Error(message = e.localizedMessage)
        }
    }
}
