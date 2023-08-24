package com.raghav.spacedawnv2.data.remote.dto

import com.raghav.spacedawnv2.domain.model.Pad
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// @JsonClass is used to make sure that Moshi uses code-gen instead of Reflection
// for Serializing and Deserializing data
@JsonClass(generateAdapter = true)
data class PadDto(
    @Json(name = "agency_id")
    val agencyId: Int?,
    @Json(name = "country_code")
    val countryCode: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "info_url")
    val infoUrl: String?,
    @Json(name = "latitude")
    val latitude: String?,
    @Json(name = "location")
    val location: LocationDto?,
    @Json(name = "longitude")
    val longitude: String?,
    @Json(name = "map_image")
    val mapImage: String?,
    @Json(name = "map_url")
    val mapUrl: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "orbital_launch_attempt_count")
    val orbitalLaunchAttemptCount: Int?,
    @Json(name = "total_launch_count")
    val totalLaunchCount: Int?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "wiki_url")
    val wikiUrl: String?
)

fun PadDto.toPad(): Pad {
    return Pad(
        agency_id = this.agencyId,
        country_code = this.countryCode,
        id = this.id,
        info_url = this.infoUrl,
        latitude = this.latitude,
        location = this.location?.toLocation(),
        longitude = this.longitude,
        map_image = this.mapImage,
        map_url = this.mapUrl,
        name = this.name,
        orbital_launch_attempt_count = this.orbitalLaunchAttemptCount,
        total_launch_count = this.totalLaunchCount,
        url = this.url,
        wiki_url = this.wikiUrl
    )
}
