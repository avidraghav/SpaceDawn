package com.raghav.spacedawnv2.domain.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "saved_launches")
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
