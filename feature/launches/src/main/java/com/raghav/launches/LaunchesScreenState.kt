package com.raghav.launches

import androidx.compose.runtime.Stable
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class LaunchesScreenState(
    val launches: ImmutableList<LaunchDetail> = persistentListOf(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
