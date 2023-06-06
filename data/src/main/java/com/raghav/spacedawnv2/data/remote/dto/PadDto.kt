package com.raghav.spacedawnv2.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.raghav.spacedawnv2.domain.model.Pad

@Keep
data class PadDto(
    @SerializedName("agency_id")
    val agencyId: Int?,
    @SerializedName("country_code")
    val countryCode: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("info_url")
    val infoUrl: Any?,
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("location")
    val location: LocationDto?,
    @SerializedName("longitude")
    val longitude: String?,
    @SerializedName("map_image")
    val mapImage: String?,
    @SerializedName("map_url")
    val mapUrl: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("orbital_launch_attempt_count")
    val orbitalLaunchAttemptCount: Int?,
    @SerializedName("total_launch_count")
    val totalLaunchCount: Int?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("wiki_url")
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
