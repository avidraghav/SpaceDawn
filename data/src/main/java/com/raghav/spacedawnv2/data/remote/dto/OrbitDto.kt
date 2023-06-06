package com.raghav.spacedawnv2.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.raghav.spacedawnv2.domain.model.Orbit

@Keep
data class OrbitDto(
    @SerializedName("abbrev")
    val abbrev: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)

fun OrbitDto.toOrbit(): Orbit {
    return Orbit(abbrev = abbrev, id = id, name = name)
}
