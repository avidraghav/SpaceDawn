package com.raghav.spacedawnv2.launchesscreen

import com.raghav.spacedawnv2.domain.model.LaunchDetail

data class LaunchesScreenState(
    val launches: List<LaunchDetail>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
