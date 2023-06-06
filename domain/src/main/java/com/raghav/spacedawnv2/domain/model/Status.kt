package com.raghav.spacedawnv2.domain.model

import androidx.annotation.Keep

@Keep
data class Status(
    val abbrev: String?,
    val description: String?,
    val id: Int?,
    val name: String?
)
