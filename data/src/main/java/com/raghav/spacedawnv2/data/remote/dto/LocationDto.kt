package com.raghav.spacedawnv2.data.remote.dto

import androidx.annotation.Keep
import com.raghav.spacedawnv2.domain.model.Location
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// @JsonClass is used to make sure that Moshi uses code-gen instead of Reflection
// for Serializing and Deserializing data
@JsonClass(generateAdapter = true)
@Keep
data class LocationDto(
    @Json(name = "country_code")
    val countryCode: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "map_image")
    val mapImage: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "timezone_name")
    val timezoneName: String?,
    @Json(name = "total_landing_count")
    val totalLandingCount: Int?,
    @Json(name = "total_launch_count")
    val totalLaunchCount: Int?,
    @Json(name = "url")
    val url: String?
)

fun LocationDto.toLocation(): Location {
    return Location(
        country_code = this.countryCode,
        id = this.id,
        map_image = this.mapImage,
        name = this.name,
        timezone_name = this.timezoneName,
        total_landing_count = this.totalLandingCount,
        total_launch_count = this.totalLaunchCount,
        url = this.url
    )
}
