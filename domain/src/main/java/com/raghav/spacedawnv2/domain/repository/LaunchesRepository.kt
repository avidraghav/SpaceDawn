package com.raghav.spacedawnv2.domain.repository

import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.model.LaunchesResponse
import com.raghav.spacedawnv2.domain.util.Resource

interface LaunchesRepository {

    suspend fun getLaunches(): Resource<LaunchesResponse?>

    suspend fun saveReminderInDb(launchDetail: LaunchDetail)
}
