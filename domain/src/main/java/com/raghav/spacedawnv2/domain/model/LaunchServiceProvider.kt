package com.raghav.spacedawnv2.domain.model

import androidx.annotation.Keep

@Keep
data class LaunchServiceProvider(
    val id: Int?,
    val name: String?,
    val type: String?,
    val url: String?
)
