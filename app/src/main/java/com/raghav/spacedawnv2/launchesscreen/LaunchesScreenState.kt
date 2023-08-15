package com.raghav.spacedawnv2.launchesscreen

import com.raghav.spacedawnv2.domain.model.LaunchDetail

data class LaunchesScreenState(
    val launches: List<LaunchDetail> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
