package com.raghav.spacedawnv2.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.raghav.spacedawnv2.data.remote.dto.LaunchDetailDto
import com.raghav.spacedawnv2.data.remote.dto.toLaunchDetail
import com.raghav.spacedawnv2.data.util.launchDetailDtoString1
import com.raghav.spacedawnv2.data.util.launchDetailDtoString2
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.model.Reminder
import com.raghav.spacedawnv2.domain.model.toReminder
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Contains Test cases for verifying the working
 * of LaunchesDao
 *
 * Test cases follow naming convention:
 * unitUnderTest_inputProvidedToUnit_resultExpected
 *
 */
class LaunchesDaoTest {

    private lateinit var database: SpaceDawnDatabase
    private lateinit var dao: LaunchesDao

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val moshi = Moshi.Builder().build()

    @Before
    fun createDb() {
        val typeConverter = Convertors(moshi)
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SpaceDawnDatabase::class.java
        ).allowMainThreadQueries().addTypeConverter(typeConverter)
            .build()

        dao = database.getLaunchesDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun cacheLaunches_launchesList_savesTheListOfLaunchesInDb() = runTest {
        val dtoObject1 = getLaunchDetailDtoFromJson(launchDetailDtoString1)
        val dtoObject2 = getLaunchDetailDtoFromJson(launchDetailDtoString2)
        val launch1 = dtoObject1?.toLaunchDetail()
        val launch2 = dtoObject2?.toLaunchDetail()

        if (launch1 != null && launch2 != null) {
            val launchesToBeCached = listOf(launch1, launch2)
            dao.cacheLaunches(launchesToBeCached)

            val cachedLaunches = dao.getLaunches().first()

            assertThat(cachedLaunches).isEqualTo(launchesToBeCached)
        }
    }

    @Test
    fun getLaunches_returnsFlowContainingListOfCachedLaunches() = runTest {
        val dtoObject1 = getLaunchDetailDtoFromJson(launchDetailDtoString1)
        val dtoObject2 = getLaunchDetailDtoFromJson(launchDetailDtoString2)
        val launch1 = dtoObject1?.toLaunchDetail()
        val launch2 = dtoObject2?.toLaunchDetail()

        if (launch1 != null && launch2 != null) {
            val cachedLaunches = listOf(launch1, launch2)
            dao.cacheLaunches(cachedLaunches)

            val launches = dao.getLaunches()
            val result = launches.firstOrNull()

            assertThat(result).isEqualTo(cachedLaunches)
        }
    }

    @Test
    fun getLaunches_returnsFlowContainingEmptyListIfNoCacheAvailable() = runTest {
        val launches = dao.getLaunches()
        val result = launches.firstOrNull()

        assertThat(result).isEqualTo(emptyList<LaunchDetail>())
    }

    @Test
    fun saveReminder_reminder_reminderIsSavedInDb() = runTest {
        val reminder = getReminderFromLaunch()

        reminder?.let {
            dao.saveReminder(it)
            val savedReminders = database.getRemindersDao().getReminders().firstOrNull()

            assertThat(reminder).isEqualTo(savedReminders?.first())
        }
    }

    @Test
    fun saveReminder_reminderWithSameIdTwice_replacesThePreviouslySavedReminderWithSameId() =
        runTest {
            val reminder = getReminderFromLaunch()

            reminder?.let {
                dao.saveReminder(it)
                dao.saveReminder(it)

                val savedReminders = database.getRemindersDao().getReminders().firstOrNull()

                assertThat(savedReminders).hasSize(1)
            }
        }

    private fun getLaunchDetailDtoFromJson(jsonString: String): LaunchDetailDto? {
        val adapter: JsonAdapter<LaunchDetailDto> =
            moshi.adapter(LaunchDetailDto::class.java)

        return adapter.fromJson(jsonString)
    }

    private fun getReminderFromLaunch(): Reminder? {
        val dtoObject = getLaunchDetailDtoFromJson(launchDetailDtoString1)
        val launch = dtoObject?.toLaunchDetail()
        return launch?.toReminder()
    }
}
