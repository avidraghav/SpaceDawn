package com.raghav.spacedawnv2.data.remote.dto

import com.raghav.spacedawnv2.domain.model.Orbit
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// @JsonClass is used to make sure that Moshi uses code-gen instead of Reflection
// for Serializing and Deserializing data
@JsonClass(generateAdapter = true)
data class OrbitDto(
    @Json(name = "abbrev")
    val abbrev: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)

fun OrbitDto.toOrbit(): Orbit {
    return Orbit(abbrev = abbrev, id = id, name = name)
}
