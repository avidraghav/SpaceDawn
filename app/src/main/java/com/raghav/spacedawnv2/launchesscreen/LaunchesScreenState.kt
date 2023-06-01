package com.raghav.spacedawnv2.launchesscreen

import com.raghav.spacedawnv2.domain.model.LaunchDetail

data class LaunchesScreenState(
    val isLoading: Boolean = false,
    val launches: List<LaunchDetail> = emptyList(),
    val error: String? = null
)
