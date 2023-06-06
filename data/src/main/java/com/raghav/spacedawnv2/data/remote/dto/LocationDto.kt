package com.raghav.spacedawnv2.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.raghav.spacedawnv2.domain.model.Location

@Keep
data class LocationDto(
    @SerializedName("country_code")
    val countryCode: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("map_image")
    val mapImage: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("timezone_name")
    val timezoneName: String?,
    @SerializedName("total_landing_count")
    val totalLandingCount: Int?,
    @SerializedName("total_launch_count")
    val totalLaunchCount: Int?,
    @SerializedName("url")
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
