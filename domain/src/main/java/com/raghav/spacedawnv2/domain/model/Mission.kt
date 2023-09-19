package com.raghav.spacedawnv2.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Mission(
    val description: String?,
    val id: Int?,
    val launch_designator: String?,
    val name: String?,
    val orbit: Orbit?,
    val type: String?
)
