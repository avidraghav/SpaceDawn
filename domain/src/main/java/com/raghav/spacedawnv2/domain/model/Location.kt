package com.raghav.spacedawnv2.domain.model

import androidx.annotation.Keep

@Keep
data class Location(
    val country_code: String?,
    val id: Int?,
    val map_image: String?,
    val name: String?,
    val timezone_name: String?,
    val total_landing_count: Int?,
    val total_launch_count: Int?,
    val url: String?
)
