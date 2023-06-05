package com.raghav.spacedawnv2.launchesscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raghav.spacedawnv2.domain.usecase.GetLaunchesUseCase
import com.raghav.spacedawnv2.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchesScreenVM @Inject constructor(
    private val getLaunchesUseCase: GetLaunchesUseCase,
    private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState: MutableStateFlow<LaunchesScreenState> =
        MutableStateFlow(LaunchesScreenState())
    val uiState: StateFlow<LaunchesScreenState> = _uiState

    init {
        getLaunches()
    }

    private fun getLaunches() {
        viewModelScope.launch(mainDispatcher) {
            _uiState.emit(LaunchesScreenState(isLoading = true))
            getLaunchesUseCase().let { result ->
                when (result) {
                    is Resource.Error -> {
                        _uiState.emit(LaunchesScreenState(error = result.errorMessage))
                    }

                    is Resource.Success -> {
                        val launches = result.data?.results?.filterNotNull() ?: emptyList()
                        _uiState.emit(LaunchesScreenState(launches = launches))
                    }
                }
            }
        }
    }
}
