package com.raghav.spacedawnv2.domain.repository

import com.raghav.spacedawnv2.domain.model.LaunchesResponse

interface LaunchesRepository {

    suspend fun getLaunches(): LaunchesResponse
}
