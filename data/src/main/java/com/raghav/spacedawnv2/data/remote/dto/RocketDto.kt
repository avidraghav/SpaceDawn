package com.raghav.spacedawnv2.data.remote.dto

import androidx.annotation.Keep
import com.raghav.spacedawnv2.domain.model.Rocket
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// @JsonClass is used to make sure that Moshi uses code-gen instead of Reflection
// for Serializing and Deserializing data
@JsonClass(generateAdapter = true)
@Keep
data class RocketDto(
    @Json(name = "configuration")
    val configuration: ConfigurationDto?,
    @Json(name = "id")
    val id: Int?
)

fun RocketDto.toRocket(): Rocket {
    return Rocket(
        configuration = this.configuration?.toConfiguration(),
        id = this.id
    )
}
