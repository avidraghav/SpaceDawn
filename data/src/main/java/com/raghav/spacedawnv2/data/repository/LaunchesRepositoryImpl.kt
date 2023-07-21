package com.raghav.spacedawnv2.data.repository

import com.raghav.spacedawnv2.data.local.LaunchesDao
import com.raghav.spacedawnv2.data.remote.LaunchesApi
import com.raghav.spacedawnv2.data.remote.dto.toDomain
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.model.LaunchesResponse
import com.raghav.spacedawnv2.domain.repository.LaunchesRepository
import javax.inject.Inject

class LaunchesRepositoryImpl @Inject constructor(
    private val launchesApi: LaunchesApi,
    private val launchesDao: LaunchesDao
) : LaunchesRepository {
    override suspend fun getLaunches(): LaunchesResponse {
        return launchesApi.getLaunches().toDomain()
    }

    override suspend fun saveReminderInDb(launchDetail: LaunchDetail) {
        launchesDao.saveLaunch(launchDetail)
    }
}
