package com.raghav.spacedawnv2.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.raghav.spacedawnv2.domain.model.LaunchDetail

@Keep
data class LaunchDetailDto(
    @SerializedName("agency_launch_attempt_count")
    val agencyLaunchAttemptCount: Int?,
    @SerializedName("agency_launch_attempt_count_year")
    val agencyLaunchAttemptCountYear: Int?,
    @SerializedName("failreason")
    val failreason: String?,
    @SerializedName("hashtag")
    val hashtag: Any?,
    @SerializedName("holdreason")
    val holdreason: String?,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String?,
    @SerializedName("infographic")
    val infographic: Any?,
    @SerializedName("last_updated")
    val lastUpdated: String?,
    @SerializedName("launch_service_provider")
    val launchServiceProviderDto: LaunchServiceProviderDto?,
    @SerializedName("location_launch_attempt_count")
    val locationLaunchAttemptCount: Int?,
    @SerializedName("location_launch_attempt_count_year")
    val locationLaunchAttemptCountYear: Int?,
    @SerializedName("mission")
    val missionDto: MissionDto?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("net")
    val net: String?,
    @SerializedName("net_precision")
    val netPrecision: NetPrecisionDto?,
    @SerializedName("orbital_launch_attempt_count")
    val orbitalLaunchAttemptCount: Int?,
    @SerializedName("orbital_launch_attempt_count_year")
    val orbitalLaunchAttemptCountYear: Int?,
    @SerializedName("pad")
    val pad: PadDto?,
    @SerializedName("pad_launch_attempt_count")
    val padLaunchAttemptCount: Int?,
    @SerializedName("pad_launch_attempt_count_year")
    val padLaunchAttemptCountYear: Int?,
    @SerializedName("probability")
    val probability: Any?,
    @SerializedName("program")
    val program: List<ProgramDto?>?,
    @SerializedName("rocket")
    val rocketDto: RocketDto?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("status")
    val statusDto: StatusDto?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("webcast_live")
    val webcastLive: Boolean?,
    @SerializedName("window_end")
    val windowEnd: String?,
    @SerializedName("window_start")
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
