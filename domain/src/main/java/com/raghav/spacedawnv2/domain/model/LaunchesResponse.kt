package com.raghav.spacedawnv2.domain.model

import androidx.annotation.Keep

@Keep
data class LaunchesResponse(
    val count: Int?,
    val next: String?,
    val previous: Any?,
    val results: List<LaunchDetail?>?
)
