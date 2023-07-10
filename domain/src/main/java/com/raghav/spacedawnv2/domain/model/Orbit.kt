package com.raghav.spacedawnv2.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Orbit(
    val abbrev: String?,
    val id: Int?,
    val name: String?
)
