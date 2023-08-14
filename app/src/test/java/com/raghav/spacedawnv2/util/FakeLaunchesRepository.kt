package com.raghav.spacedawnv2.util

import com.raghav.spacedawnv2.data.remote.dto.LaunchesResponseDto
import com.raghav.spacedawnv2.data.remote.dto.toLaunchDetail
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.model.Reminder
import com.raghav.spacedawnv2.domain.repository.LaunchesRepository
import com.raghav.spacedawnv2.domain.util.Resource
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeLaunchesRepository(
    private val successOutput: Boolean = true
) : LaunchesRepository {
    private val moshi = Moshi.Builder().build()

    override fun getLaunches(): Flow<Resource<List<LaunchDetail>>> {
        val adapter: JsonAdapter<LaunchesResponseDto> =
            moshi.adapter(LaunchesResponseDto::class.java)
        val dto =
            adapter.fromJson(launchesResponseDtoString)
        val launches = dto?.results?.filterNotNull()
            ?.map { it.toLaunchDetail() } ?: emptyList()

        return if (successOutput) {
            flowOf(Resource.Success(launches))
        } else {
            flowOf(Resource.Error("some error message"))
        }
    }

    override suspend fun saveReminder(reminder: Reminder) {
        // do nothing
    }
}
