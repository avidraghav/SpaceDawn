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
 */

/**
 * Fetches Upcoming Launches
 * @param repository [LaunchesRepository]
 *
 * @return Resource<[LaunchesResponse]>
 *
 * @see Resource
 */
class GetLaunchesUseCase @Inject constructor(
    private val repository: LaunchesRepository
) {
    suspend operator fun invoke(): Resource<LaunchesResponse?> {
        return try {
            when (val result = repository.getLaunches()) {
                is Resource.Error -> {
                    Resource.Error(result.errorMessage.toString())
                }

                is Resource.Success -> {
                    Resource.Success(result.data)
                }
            }
        } catch (e: Exception) {
            // handle errors such as No Internet Connection
            Resource.Error(message = e.localizedMessage)
        }
    }
}
