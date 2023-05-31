package com.raghav.spacedawnv2.data.remote

import com.raghav.spacedawnv2.data.remote.dto.LaunchesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface LaunchesApi {

    @GET("upcoming")
    suspend fun getLaunches(
        @Query("offset")
        offset: Int = 0
    ): List<LaunchesResponseDto>
}
