package com.raghav.spacedawnv2.data.remote.dto

import androidx.annotation.Keep
import com.raghav.spacedawnv2.domain.model.NetPrecision
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// @JsonClass is used to make sure that Moshi uses code-gen instead of Reflection
// for Serializing and Deserializing data
@JsonClass(generateAdapter = true)
@Keep
data class NetPrecisionDto(
    @Json(name = "abbrev")
    val abbrev: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)

fun NetPrecisionDto.toNetPrecision(): NetPrecision {
    return NetPrecision(abbrev, description, id, name)
}
