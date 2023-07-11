package com.raghav.spacedawnv2.launchesscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.usecase.AddReminderUseCase
import com.raghav.spacedawnv2.domain.usecase.GetLaunchesUseCase
import com.raghav.spacedawnv2.domain.util.Constants
import com.raghav.spacedawnv2.domain.util.Resource
import com.raghav.spacedawnv2.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LaunchesScreenVM @Inject constructor(
    private val getLaunchesUseCase: GetLaunchesUseCase,
    private val dispatchers: DispatchersProvider,
    private val addReminderUseCase: AddReminderUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<LaunchesScreenState> =
        MutableStateFlow(LaunchesScreenState())
    val uiState: StateFlow<LaunchesScreenState> = _uiState.asStateFlow()

    private val _eventFlow: MutableSharedFlow<LaunchesScreenEvent> = MutableSharedFlow()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getLaunches()
    }

    private fun getLaunches() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getLaunchesUseCase().let { result ->
                when (result) {
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Success -> {
                        val launches = result.data?.results?.filterNotNull() ?: emptyList()
                        _uiState.update {
                            it.copy(
                                launches = launches,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    fun setReminder(launch: LaunchDetail) {
        viewModelScope.launch(dispatchers.default) {
            addReminderUseCase(launch).let { result ->
                when (result) {
                    is Resource.Error -> {
                        if (result.errorMessage == Constants.ALARM_PERMISSION_NOT_AVAILABLE) {
                            _eventFlow.emit(LaunchesScreenEvent.PermissionToSetReminderNotGranted)
                        } else {
                            _eventFlow.emit(LaunchesScreenEvent.ReminderNotSet)
                        }
                    }

                    is Resource.Success -> {
                        _eventFlow.emit(LaunchesScreenEvent.ReminderSetSuccessfully)
                    }
                }
            }
        }
    }
}

sealed class LaunchesScreenEvent {
    object ReminderSetSuccessfully : LaunchesScreenEvent()
    object ReminderNotSet : LaunchesScreenEvent()
    object PermissionToSetReminderNotGranted :
        LaunchesScreenEvent()
}
