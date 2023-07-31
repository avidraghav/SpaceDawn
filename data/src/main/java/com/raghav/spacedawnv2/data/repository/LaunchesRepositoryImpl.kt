package com.raghav.spacedawnv2.data.repository

import com.raghav.spacedawnv2.data.local.LaunchesDao
import com.raghav.spacedawnv2.data.remote.LaunchesApi
import com.raghav.spacedawnv2.data.remote.dto.toDomain
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.model.LaunchesResponse
import com.raghav.spacedawnv2.domain.repository.LaunchesRepository
import com.raghav.spacedawnv2.domain.util.Resource
import javax.inject.Inject

class LaunchesRepositoryImpl @Inject constructor(
    private val launchesApi: LaunchesApi,
    private val launchesDao: LaunchesDao
) : LaunchesRepository {
    override suspend fun getLaunches(): Resource<LaunchesResponse?> {
        val request = launchesApi.getLaunches()
        return if (request.isSuccessful) {
            Resource.Success(data = request.body()?.toDomain())
        } else {
            if (request.code() == 429) {
                Resource.Error("Requests Limit Reached, please try after 1 hour")
            } else {
                Resource.Error("Some Unknown Error Occurred")
            }
        }
    }

    override suspend fun saveReminderInDb(launchDetail: LaunchDetail) {
        launchesDao.saveLaunch(launchDetail)
    }
}
