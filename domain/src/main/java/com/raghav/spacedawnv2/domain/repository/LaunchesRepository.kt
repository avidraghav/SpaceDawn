package com.raghav.spacedawnv2.domain.repository

import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.model.LaunchesResponse

interface LaunchesRepository {

    suspend fun getLaunches(): LaunchesResponse

    suspend fun saveReminderInDb(launchDetail: LaunchDetail)
}
