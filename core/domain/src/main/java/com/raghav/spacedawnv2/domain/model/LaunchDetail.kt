package com.raghav.spacedawnv2.domain.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * this entity represents a Launch object which is showed on the UI
 */
@Keep
@Entity(tableName = "cached_launches")
data class LaunchDetail(
    val agencyLaunchAttemptCount: Int?,
    val agencyLaunchAttemptCountYear: Int?,
    val failreason: String?,
    val holdreason: String?,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val image: String?,
    val infographic: String?,
    val lastUpdated: String?,
    val launchServiceProvider: LaunchServiceProvider?,
    val locationLaunchAttemptCount: Int?,
    val locationLaunchAttemptCountYear: Int?,
    val mission: Mission?,
    val name: String?,
    val net: String,
    val netPrecision: NetPrecision?,
    val orbitalLaunchAttemptCount: Int?,
    val orbitalLaunchAttemptCountYear: Int?,
    val pad: Pad?,
    val padLaunchAttemptCount: Int?,
    val padLaunchAttemptCountYear: Int?,
    val probability: String?,
    val rocket: Rocket?,
    val slug: String?,
    val status: Status?,
    val url: String?,
    val webcastLive: Boolean?,
    val windowEnd: String?,
    val windowStart: String?
)

fun LaunchDetail.toReminder(): Reminder {
    return Reminder(
        agencyLaunchAttemptCount = agencyLaunchAttemptCount,
        agencyLaunchAttemptCountYear = agencyLaunchAttemptCountYear,
        failreason = failreason,
        holdreason = holdreason,
        id = id,
        image = image,
        infographic = infographic,
        lastUpdated = lastUpdated,
        launchServiceProvider = launchServiceProvider,
        locationLaunchAttemptCount = locationLaunchAttemptCount,
        locationLaunchAttemptCountYear = locationLaunchAttemptCountYear,
        mission = mission,
        name = name,
        net = net,
        netPrecision = netPrecision,
        orbitalLaunchAttemptCount = orbitalLaunchAttemptCount,
        orbitalLaunchAttemptCountYear = orbitalLaunchAttemptCountYear,
        pad = pad,
        padLaunchAttemptCount = padLaunchAttemptCount,
        padLaunchAttemptCountYear = padLaunchAttemptCountYear,
        probability = probability,
        rocket = rocket,
        slug = slug,
        status = status,
        url = url,
        webcastLive = webcastLive,
        windowEnd = windowEnd,
        windowStart = windowStart
    )
}
