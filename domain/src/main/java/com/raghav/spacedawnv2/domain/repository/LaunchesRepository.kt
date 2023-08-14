package com.raghav.spacedawnv2.domain.repository

import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.model.Reminder
import com.raghav.spacedawnv2.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface LaunchesRepository {

    fun getLaunches(): Flow<Resource<List<LaunchDetail>>>

    suspend fun createReminder(reminder: Reminder)
}
