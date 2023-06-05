package com.raghav.spacedawnv2.launchesscreen

import com.raghav.spacedawnv2.domain.model.LaunchDetail

sealed class LaunchesScreenState(
    val launches: List<LaunchDetail> = emptyList(),
    val errorMessage: String? = null
) {
    object Loading : LaunchesScreenState()
    class Success(launches: List<LaunchDetail>) : LaunchesScreenState(launches = launches)
    class Error(message: String?) : LaunchesScreenState(errorMessage = message)
    object Empty : LaunchesScreenState()
}
