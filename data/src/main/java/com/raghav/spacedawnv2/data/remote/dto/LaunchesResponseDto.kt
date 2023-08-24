package com.raghav.spacedawnv2.data.remote.dto

import com.raghav.spacedawnv2.domain.model.LaunchesResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.collections.immutable.toImmutableList

// @JsonClass is used to make sure that Moshi uses code-gen instead of Reflection
// for Serializing and Deserializing data
@JsonClass(generateAdapter = true)
data class LaunchesResponseDto(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "next")
    val next: String?,
    @Json(name = "previous")
    val previous: String?,
    @Json(name = "results")
    val results: List<LaunchDetailDto?>?
)

fun LaunchesResponseDto.toDomain(): LaunchesResponse {
    return LaunchesResponse(
        count = count,
        next = next,
        previous = previous,
        results = results?.map { it?.toLaunchDetail() }?.toImmutableList()
    )
}
