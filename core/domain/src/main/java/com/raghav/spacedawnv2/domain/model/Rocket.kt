package com.raghav.spacedawnv2.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rocket(
    val configuration: Configuration?,
    val id: Int?
)
