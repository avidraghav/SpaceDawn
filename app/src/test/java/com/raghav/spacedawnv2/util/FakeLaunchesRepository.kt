package com.raghav.spacedawnv2.util

import com.raghav.spacedawnv2.data.remote.dto.LaunchesResponseDto
import com.raghav.spacedawnv2.data.remote.dto.toDomain
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.model.LaunchesResponse
import com.raghav.spacedawnv2.domain.repository.LaunchesRepository
import com.raghav.spacedawnv2.domain.util.Constants
import com.raghav.spacedawnv2.domain.util.Resource
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class FakeLaunchesRepository(
    private val successOutput: Boolean? = null,
    private val errorCode: Int? = null
) : LaunchesRepository {
    private val moshi = Moshi.Builder().build()
    override suspend fun getLaunches(): Resource<LaunchesResponse?> {
        return if (successOutput != null) {
            if (successOutput == true) {
                val adapter: JsonAdapter<LaunchesResponseDto> =
                    moshi.adapter(LaunchesResponseDto::class.java)

                Resource.Success(adapter.fromJson(launchesResponseDtoString)?.toDomain())
            } else {
                if (errorCode == 429) {
                    Resource.Error(Constants.API_THROTTLED_MESSAGE)
                } else {
                    Resource.Error(Constants.UNKNOWN_ERROR_MESSAGE)
                }
            }
        } else {
            Resource.Error("")
        }
    }

    override suspend fun saveReminderInDb(launchDetail: LaunchDetail) {
    }
}
