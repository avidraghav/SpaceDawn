package com.raghav.spacedawnv2.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.raghav.spacedawnv2.domain.model.Mission

@Keep
data class MissionDto(
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("launch_designator")
    val launchDesignator: Any?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("orbit")
    val orbit: OrbitDto?,
    @SerializedName("type")
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
