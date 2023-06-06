package com.raghav.spacedawnv2.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.raghav.spacedawnv2.domain.model.LaunchesResponse

@Keep
data class LaunchesResponseDto(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: Any?,
    @SerializedName("results")
    val results: List<LaunchDetailDto?>?
)

fun LaunchesResponseDto.toDomain(): LaunchesResponse {
    return LaunchesResponse(
        count = count,
        next = next,
        previous = previous,
        results = results?.map { it?.toLaunchDetail() }
    )
}
