package com.raghav.spacedawnv2.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LaunchServiceProvider(
    val id: Int?,
    val name: String?,
    val type: String?,
    val url: String?
)
