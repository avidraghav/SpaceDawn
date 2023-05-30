package com.raghav.spacedawnv2.domain.model

import androidx.annotation.Keep

@Keep
data class NetPrecision(
    val abbrev: String?,
    val description: String?,
    val id: Int?,
    val name: String?
)
