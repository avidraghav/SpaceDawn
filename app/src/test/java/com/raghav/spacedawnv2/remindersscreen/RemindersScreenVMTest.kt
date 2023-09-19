package com.raghav.spacedawnv2.remindersscreen

import com.google.common.truth.Truth
import com.raghav.spacedawnv2.data.remote.dto.LaunchesResponseDto
import com.raghav.spacedawnv2.data.remote.dto.toDomain
import com.raghav.spacedawnv2.domain.model.Reminder
import com.raghav.spacedawnv2.domain.model.toReminder
import com.raghav.spacedawnv2.domain.usecase.CancelReminderUseCase
import com.raghav.spacedawnv2.domain.usecase.GetRemindersUseCase
import com.raghav.spacedawnv2.util.FakeReminderScheduler
import com.raghav.spacedawnv2.util.FakeRemindersRepository
import com.raghav.spacedawnv2.util.Helpers.Companion.getDtoFromJson
import com.raghav.spacedawnv2.util.MainDispatcherRule
import com.raghav.spacedawnv2.util.launchesResponseDtoString
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RemindersScreenVMTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var remindersScreenVM: RemindersScreenVM

    /* RemindersScreenState(
         val reminders: ImmutableList<Reminder> = persistentListOf(),
         val isLoading: Boolean = false,
         val infoMessage: String? = "Some Error Occurred"
       )
     */

    @Test
    fun getReminders_errorOccurred_updatesUiState() = runTest {
        initialize(false, testScheduler = testScheduler)
        val result = remindersScreenVM.uiState.value
        val expected = RemindersScreenState(infoMessage = "Some Error Occurred")

        Truth.assertThat(result).isEqualTo(expected)
    }

    /* RemindersScreenState(
       val reminders: ImmutableList<Reminder> = remimders,
       val isLoading: Boolean = false,
       val infoMessage: String? = null
     )
   */

    @Test
    fun getReminders_remindersAvailable_updateUiState() = runTest {
        initialize(remindersFetchedSuccessfully = true, testScheduler = testScheduler)
        val result = remindersScreenVM.uiState.value

        val reminders = createReminders()

        val expected = RemindersScreenState(reminders = reminders.toImmutableList())

        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun cancelReminder_removesReminderFromDeviceAndAppDatabase() = runTest {
        initialize(testScheduler = testScheduler)

        val reminders = createReminders()

        var result: RemindersScreenEvent? = null
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            remindersScreenVM.eventFlow.collect {
                result = it
            }
        }
        remindersScreenVM.cancelReminder(reminders.first().id)
        val expected = RemindersScreenEvent.ReminderCancelled

        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun cancelReminder_inCaseOfFailureEmitFailureEvent() = runTest {
        // assuming failure occurred while deleting the reminder from database
        initialize(reminderDeletedSuccessfully = false, testScheduler = testScheduler)

        val reminders = createReminders()

        var result: RemindersScreenEvent? = null
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            remindersScreenVM.eventFlow.collect {
                result = it
            }
        }
        remindersScreenVM.cancelReminder(reminders.first().id)
        val expected = RemindersScreenEvent.ReminderNotCancelled("Some Error Occurred")

        Truth.assertThat(result).isEqualTo(expected)
    }

    private fun initialize(
        remindersFetchedSuccessfully: Boolean = true,
        reminderDeletedSuccessfully: Boolean = true,
        testScheduler: TestCoroutineScheduler
    ) {
        val fakeRemindersRepository =
            FakeRemindersRepository(remindersFetchedSuccessfully, reminderDeletedSuccessfully)
        val fakeReminderScheduler = FakeReminderScheduler()
        val getReminderUseCase = GetRemindersUseCase(fakeRemindersRepository)
        val cancelReminderUseCase =
            CancelReminderUseCase(fakeRemindersRepository, fakeReminderScheduler)
        remindersScreenVM =
            RemindersScreenVM(
                cancelReminderUseCase = cancelReminderUseCase,
                getRemindersUseCase = getReminderUseCase,
                ioDispatcher = UnconfinedTestDispatcher(testScheduler)
            )
    }

    private fun createReminders(): List<Reminder> {
        val launchesResponse =
            getDtoFromJson<LaunchesResponseDto>(launchesResponseDtoString)?.toDomain()

        return launchesResponse?.results?.filterNotNull()?.map {
            it.toReminder()
        } ?: emptyList()
    }
}
