package com.raghav.spacedawnv2.domain.model

import androidx.annotation.Keep

@Keep
data class LaunchDetail(
    val agencyLaunchAttemptCount: Int?,
    val agencyLaunchAttemptCountYear: Int?,
    val failreason: String?,
    val hashtag: Any?,
    val holdreason: String?,
    val id: String?,
    val image: String?,
    val infographic: Any?,
    val lastUpdated: String?,
    val launchServiceProvider: LaunchServiceProvider?,
    val locationLaunchAttemptCount: Int?,
    val locationLaunchAttemptCountYear: Int?,
    val mission: Mission?,
    val name: String?,
    val net: String?,
    val netPrecision: NetPrecision?,
    val orbitalLaunchAttemptCount: Int?,
    val orbitalLaunchAttemptCountYear: Int?,
    val pad: Pad?,
    val padLaunchAttemptCount: Int?,
    val padLaunchAttemptCountYear: Int?,
    val probability: Any?,
    val program: List<Program?>?,
    val rocket: Rocket?,
    val slug: String?,
    val status: Status?,
    val url: String?,
    val webcastLive: Boolean?,
    val windowEnd: String?,
    val windowStart: String?
)
