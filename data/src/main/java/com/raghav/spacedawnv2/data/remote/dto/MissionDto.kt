package com.raghav.spacedawnv2.data.remote.dto

import com.raghav.spacedawnv2.domain.model.Mission
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// @JsonClass is used to make sure that Moshi uses code-gen instead of Reflection
// for Serializing and Deserializing data
@JsonClass(generateAdapter = true)
data class MissionDto(
    @Json(name = "description")
    val description: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "launch_designator")
    val launchDesignator: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "orbit")
    val orbit: OrbitDto?,
    @Json(name = "type")
    val type: String?
)

fun MissionDto.toMission(): Mission {
    return Mission(
        description = description,
        id = id,
        launch_designator = launchDesignator,
        name = name,
        orbit = orbit?.toOrbit(),
        type = type
    )
}
