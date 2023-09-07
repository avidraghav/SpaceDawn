package com.raghav.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.model.toReminder
import com.raghav.spacedawnv2.domain.repository.LaunchesRepository
import com.raghav.spacedawnv2.domain.usecase.AddReminderUseCase
import com.raghav.spacedawnv2.domain.util.Constants
import com.raghav.spacedawnv2.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LaunchesScreenVM @Inject constructor(
    private val launchesRepository: LaunchesRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
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
            launchesRepository.getLaunches()
                .collect { result ->
                    when (result) {
                        is Resource.Error -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = result.errorMessage,
                                    launches = result.data?.toImmutableList() ?: persistentListOf()
                                )
                            }
                        }

                        is Resource.Success -> {
                            _uiState.update {
                                it.copy(
                                    launches = result.data?.toImmutableList() ?: persistentListOf(),
                                    isLoading = false,
                                    errorMessage = null
                                )
                            }
                        }
                    }
                }
        }
    }

    fun setReminder(launch: LaunchDetail) {
        viewModelScope.launch(ioDispatcher) {
            val reminder = launch.toReminder()
            addReminderUseCase(reminder).let { result ->
                when (result) {
                    is Resource.Error -> {
                        when (result.errorMessage) {
                            Constants.REMINDER_PERMISSION_NOT_AVAILABLE -> {
                                _eventFlow.emit(LaunchesScreenEvent.PermissionToSetReminderNotGranted)
                            }

                            Constants.NOTIFICATION_PERMISSION_NOT_AVAILABLE -> {
                                _eventFlow.emit(LaunchesScreenEvent.PermissionToSendNotificationsNotGranted)
                            }

                            Constants.NOTIFICATION_REMINDER_PERMISSION_NOT_AVAILABLE -> {
                                _eventFlow.emit(LaunchesScreenEvent.PermissionsToSendNotificationsAndSetReminderNotGranted)
                            }

                            else -> {
                                _eventFlow.emit(LaunchesScreenEvent.ReminderNotSet(result.errorMessage))
                            }
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

sealed class LaunchesScreenEvent(
    val infoMessage: String? = null
) {
    object ReminderSetSuccessfully : LaunchesScreenEvent()
    data class ReminderNotSet(val errorMessage: String?) : LaunchesScreenEvent(errorMessage)
    object PermissionToSetReminderNotGranted : LaunchesScreenEvent()
    object PermissionToSendNotificationsNotGranted : LaunchesScreenEvent()
    object PermissionsToSendNotificationsAndSetReminderNotGranted : LaunchesScreenEvent()
}
