package com.raghav.spacedawnv2.domain.model

import androidx.annotation.Keep
import kotlinx.collections.immutable.ImmutableList

@Keep
data class LaunchesResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: ImmutableList<LaunchDetail?>?
)
