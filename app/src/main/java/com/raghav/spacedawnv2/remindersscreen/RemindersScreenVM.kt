package com.raghav.spacedawnv2.remindersscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raghav.spacedawnv2.domain.usecase.CancelReminderUseCase
import com.raghav.spacedawnv2.domain.usecase.GetRemindersUseCase
import com.raghav.spacedawnv2.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class RemindersScreenVM @Inject constructor(
    private val getRemindersUseCase: GetRemindersUseCase,
    private val cancelReminderUseCase: CancelReminderUseCase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _uiState: MutableStateFlow<RemindersScreenState> =
        MutableStateFlow(RemindersScreenState())
    val uiState: StateFlow<RemindersScreenState> = _uiState.asStateFlow()

    private val _eventFlow: MutableSharedFlow<RemindersScreenEvent> = MutableSharedFlow()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getReminders()
    }

    private fun getReminders() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getRemindersUseCase()
                .catch { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            infoMessage = throwable.localizedMessage
                        )
                    }
                }
                .collect { reminders ->
                    _uiState.update {
                        it.copy(
                            reminders = reminders.toImmutableList(),
                            isLoading = false,
                            infoMessage = null
                        )
                    }
                }
        }
    }

    fun cancelReminder(reminderId: String) {
        viewModelScope.launch(ioDispatcher) {
            cancelReminderUseCase(reminderId).let { result ->
                when (result) {
                    is Resource.Error -> {
                        _eventFlow.emit(RemindersScreenEvent.ReminderNotCancelled(result.errorMessage.toString()))
                    }

                    is Resource.Success -> {
                        _eventFlow.emit(RemindersScreenEvent.ReminderCancelled)
                    }
                }
            }
        }
    }
}

sealed class RemindersScreenEvent(
    val infoMessage: String? = null
) {
    object ReminderCancelled : RemindersScreenEvent()
    data class ReminderNotCancelled(val errorMessage: String) : RemindersScreenEvent(errorMessage)
}
