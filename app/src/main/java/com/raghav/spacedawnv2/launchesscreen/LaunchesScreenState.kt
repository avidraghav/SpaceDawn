package com.raghav.spacedawnv2.launchesscreen

import androidx.compose.runtime.Immutable
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class LaunchesScreenState(
    val launches: ImmutableList<LaunchDetail> = persistentListOf(),
    val isLoading: Boolean = false,
    val infoMessage: String? = null
)
