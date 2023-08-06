package com.raghav.spacedawnv2.launchesscreen

import com.google.common.truth.Truth.assertThat
import com.raghav.spacedawnv2.data.remote.dto.LaunchDetailDto
import com.raghav.spacedawnv2.data.remote.dto.LaunchesResponseDto
import com.raghav.spacedawnv2.data.remote.dto.toDomain
import com.raghav.spacedawnv2.data.remote.dto.toLaunchDetail
import com.raghav.spacedawnv2.domain.usecase.AddReminderUseCase
import com.raghav.spacedawnv2.domain.usecase.GetLaunchesUseCase
import com.raghav.spacedawnv2.domain.util.Constants
import com.raghav.spacedawnv2.util.FakeLaunchesRepository
import com.raghav.spacedawnv2.util.FakeReminderScheduler
import com.raghav.spacedawnv2.util.MainDispatcherRule
import com.raghav.spacedawnv2.util.launchDetailDtoString
import com.raghav.spacedawnv2.util.launchesResponseDtoString
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

/**
 * Contains Test Cases to verify the working of
 * [LaunchesScreenVM]
 *
 * Test cases follow naming convention:
 * unitUnderTest_inputProvidedToUnit_resultExpected
 */

@OptIn(ExperimentalCoroutinesApi::class)
class LaunchesScreenVMTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var launchesScreenVM: LaunchesScreenVM
    private val fakeReminderScheduler = FakeReminderScheduler()

    private val moshi = Moshi.Builder().build()

    /*
    UiState = LaunchesScreenState(
       val launches: List<LaunchDetail> ,
       val isLoading: Boolean = false,
       val infoMessage: String? = null
    )
     */
    @Test
    fun getLaunches_eventSuccessful_updatesUiState() = runTest {
        initialize(testScheduler = testScheduler)

        val result = launchesScreenVM.uiState.value
        val launchesResponse =
            getDtoFromJson<LaunchesResponseDto>(launchesResponseDtoString)?.toDomain()

        val launches =
            launchesResponse?.results?.filterNotNull()
                ?: emptyList()

        val expected = LaunchesScreenState(launches = launches)

        assertThat(result).isEqualTo(expected)
    }

    /*
    UiState = LaunchesScreenState(
       val launches: List<LaunchDetail> ,
       val isLoading: Boolean = false,
       val infoMessage: String? = "Some Unknown Error Occurred"
    )
     */
    @Test
    fun getLaunches_eventUnSuccessful_updatesUiState() = runTest {
        initialize(launchesRepoOutputIsSuccess = false, testScheduler = testScheduler)
        val result = launchesScreenVM.uiState.value

        val expected = LaunchesScreenState(infoMessage = Constants.UNKNOWN_ERROR_MESSAGE)

        assertThat(result).isEqualTo(expected)
    }

    /*
    UiState = LaunchesScreenState(
       val launches: List<LaunchDetail> ,
       val isLoading: Boolean = false,
       val infoMessage: String? = "Requests Limit Reached, please try after 1 hour"
    )
    */
    @Test
    fun getLaunches_apiThrottled_updatesUiState() = runTest {
        initialize(
            launchesRepoOutputIsSuccess = false,
            errorCode = 429,
            testScheduler = testScheduler
        )

        val result = launchesScreenVM.uiState.value

        val expected = LaunchesScreenState(infoMessage = Constants.API_THROTTLED_MESSAGE)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun setReminder_launchDetail_ReminderSetSuccessfully() = runTest {
        initialize(testScheduler = testScheduler)

        val launchDetail = getDtoFromJson<LaunchDetailDto>(launchDetailDtoString)?.toLaunchDetail()
        launchDetail?.let {
            var result: LaunchesScreenEvent? = null

            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                launchesScreenVM.eventFlow.collect {
                    result = it
                }
            }

            launchesScreenVM.setReminder(launchDetail)
            val expected = LaunchesScreenEvent.ReminderSetSuccessfully

            assertThat(result).isEqualTo(expected)
        }
    }

    private fun initialize(
        launchesRepoOutputIsSuccess: Boolean = true,
        errorCode: Int = 200,
        testScheduler: TestCoroutineScheduler
    ) {
        val fakeLaunchesRepository = FakeLaunchesRepository(launchesRepoOutputIsSuccess, errorCode)
        val getLaunchesUseCase = GetLaunchesUseCase(fakeLaunchesRepository)
        val addReminderUseCase =
            AddReminderUseCase(fakeLaunchesRepository, fakeReminderScheduler)
        launchesScreenVM =
            LaunchesScreenVM(
                getLaunchesUseCase,
                UnconfinedTestDispatcher(testScheduler),
                addReminderUseCase
            )
    }

    private inline fun <reified T> getDtoFromJson(jsonString: String): T? {
        val adapter: JsonAdapter<T> =
            moshi.adapter(T::class.java)

        return adapter.fromJson(jsonString)
    }
}
