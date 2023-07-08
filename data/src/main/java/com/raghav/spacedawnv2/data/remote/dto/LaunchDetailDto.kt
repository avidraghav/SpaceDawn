package com.raghav.spacedawnv2.data.remote.dto

import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// @JsonClass is used to make sure that Moshi uses code-gen instead of Reflection
// for Serializing and Deserializing data
@JsonClass(generateAdapter = true)
data class LaunchDetailDto(
    @Json(name = "agency_launch_attempt_count")
    val agencyLaunchAttemptCount: Int?,
    @Json(name = "agency_launch_attempt_count_year")
    val agencyLaunchAttemptCountYear: Int?,
    @Json(name = "failreason")
    val failreason: String?,
    @Json(name = "hashtag")
    val hashtag: Any?,
    @Json(name = "holdreason")
    val holdreason: String?,
    @Json(name = "id")
    val id: String,
    @Json(name = "image")
    val image: String?,
    @Json(name = "infographic")
    val infographic: Any?,
    @Json(name = "last_updated")
    val lastUpdated: String?,
    @Json(name = "launch_service_provider")
    val launchServiceProviderDto: LaunchServiceProviderDto?,
    @Json(name = "location_launch_attempt_count")
    val locationLaunchAttemptCount: Int?,
    @Json(name = "location_launch_attempt_count_year")
    val locationLaunchAttemptCountYear: Int?,
    @Json(name = "mission")
    val missionDto: MissionDto?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "net")
    val net: String?,
    @Json(name = "net_precision")
    val netPrecision: NetPrecisionDto?,
    @Json(name = "orbital_launch_attempt_count")
    val orbitalLaunchAttemptCount: Int?,
    @Json(name = "orbital_launch_attempt_count_year")
    val orbitalLaunchAttemptCountYear: Int?,
    @Json(name = "pad")
    val pad: PadDto?,
    @Json(name = "pad_launch_attempt_count")
    val padLaunchAttemptCount: Int?,
    @Json(name = "pad_launch_attempt_count_year")
    val padLaunchAttemptCountYear: Int?,
    @Json(name = "probability")
    val probability: Any?,
    @Json(name = "program")
    val program: List<ProgramDto?>?,
    @Json(name = "rocket")
    val rocketDto: RocketDto?,
    @Json(name = "slug")
    val slug: String?,
    @Json(name = "status")
    val statusDto: StatusDto?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "webcast_live")
    val webcastLive: Boolean?,
    @Json(name = "window_end")
    val windowEnd: String?,
    @Json(name = "window_start")
    val windowStart: String?
)

fun LaunchDetailDto.toLaunchDetail(): LaunchDetail {
    return LaunchDetail(
        agencyLaunchAttemptCount = agencyLaunchAttemptCount,
        agencyLaunchAttemptCountYear = agencyLaunchAttemptCountYear,
        failreason = failreason,
        holdreason = holdreason,
        id = id,
        image = image,
        infographic = infographic.toString(),
        lastUpdated = lastUpdated,
        launchServiceProvider = launchServiceProviderDto?.toLaunchServiceProvider(),
        locationLaunchAttemptCount = locationLaunchAttemptCount,
        locationLaunchAttemptCountYear = locationLaunchAttemptCountYear,
        mission = missionDto?.toMission(),
        name = name,
        net = net.orEmpty(),
        netPrecision = netPrecision?.toNetPrecision(),
        orbitalLaunchAttemptCount = orbitalLaunchAttemptCount,
        orbitalLaunchAttemptCountYear = orbitalLaunchAttemptCountYear,
        pad = pad?.toPad(),
        padLaunchAttemptCount = padLaunchAttemptCount,
        padLaunchAttemptCountYear = padLaunchAttemptCountYear,
        probability = probability.toString(),
        rocket = rocketDto?.toRocket(),
        slug = slug,
        status = statusDto?.toStatus(),
        url = url,
        webcastLive = webcastLive,
        windowEnd = windowEnd,
        windowStart = windowStart
    )
}
