package com.raghav.spacedawnv2.data.remote.dto

import com.raghav.spacedawnv2.domain.model.LaunchServiceProvider
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// @JsonClass is used to make sure that Moshi uses code-gen instead of Reflection
// for Serializing and Deserializing data
@JsonClass(generateAdapter = true)
data class LaunchServiceProviderDto(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "url")
    val url: String?
)

fun LaunchServiceProviderDto.toLaunchServiceProvider(): LaunchServiceProvider {
    return LaunchServiceProvider(
        id = id,
        name = name,
        type = type,
        url = url
    )
}
