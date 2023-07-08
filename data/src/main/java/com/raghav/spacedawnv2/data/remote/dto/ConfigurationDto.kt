package com.raghav.spacedawnv2.data.remote.dto

import com.raghav.spacedawnv2.domain.model.Configuration
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// @JsonClass is used to make sure that Moshi uses code-gen instead of Reflection
// for Serializing and Deserializing data
@JsonClass(generateAdapter = true)
data class ConfigurationDto(
    @Json(name = "family")
    val family: String?,
    @Json(name = "full_name")
    val fullName: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "variant")
    val variant: String?
)

fun ConfigurationDto.toConfiguration(): Configuration {
    return Configuration(
        family = this.family,
        full_name = this.fullName,
        id = this.id,
        name = this.name,
        url = this.url,
        variant = this.variant
    )
}
