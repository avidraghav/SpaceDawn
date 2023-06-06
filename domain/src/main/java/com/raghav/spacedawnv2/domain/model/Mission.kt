package com.raghav.spacedawnv2.domain.model

import androidx.annotation.Keep

@Keep
data class Mission(
    val description: String?,
    val id: Int?,
    val launch_designator: Any?,
    val name: String?,
    val orbit: Orbit?,
    val type: String?
)
