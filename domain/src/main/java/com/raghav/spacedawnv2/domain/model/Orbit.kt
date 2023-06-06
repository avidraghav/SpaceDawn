package com.raghav.spacedawnv2.domain.model

import androidx.annotation.Keep

@Keep
data class Orbit(
    val abbrev: String?,
    val id: Int?,
    val name: String?
)
