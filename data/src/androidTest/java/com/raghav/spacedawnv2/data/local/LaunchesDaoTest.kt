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
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
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

    private lateinit var database: LaunchesDatabase
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
            LaunchesDatabase::class.java
        ).allowMainThreadQueries().addTypeConverter(typeConverter)
            .build()

        dao = database.getLaunchesDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun getSavedLaunches_returnsFlowContainingEmptyListIfNoLaunchesSaved() = runTest {
        val savedLaunches = dao.getSavedLaunches()
        val result = savedLaunches.firstOrNull()
        val expected = emptyList<List<LaunchDetail>>()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun saveLaunch_launchDetail_launchIsSavedInDb() = runTest {
        val dtoObject = getLaunchDetailDtoFromJson(launchDetailDtoString1)
        val launchDetail = dtoObject?.toLaunchDetail()

        launchDetail?.let {
            dao.saveLaunch(launchDetail)

            val savedLaunches = dao.getSavedLaunches().firstOrNull()

            assertThat(savedLaunches).containsExactly(launchDetail)
        }
    }

    @Test
    fun saveLaunch_launchDetailWithSameIdTwice_replacesThePreviouslySavedLaunchWithSameId() =
        runTest {
            val dtoObject = getLaunchDetailDtoFromJson(launchDetailDtoString1)
            val launchDetail = dtoObject?.toLaunchDetail()

            launchDetail?.let {
                dao.saveLaunch(launchDetail)
                dao.saveLaunch(launchDetail)

                val savedLaunches = dao.getSavedLaunches().firstOrNull()

                assertThat(savedLaunches).hasSize(1)
            }
        }

    @Test
    fun getSavedLaunches_returnsFlowContainingListOfLaunches() = runTest {
        val dtoObject1 = getLaunchDetailDtoFromJson(launchDetailDtoString1)
        val dtoObject2 = getLaunchDetailDtoFromJson(launchDetailDtoString2)
        val launchDetail1 = dtoObject1?.toLaunchDetail()
        val launchDetail2 = dtoObject2?.toLaunchDetail()

        if (launchDetail1 != null && launchDetail2 != null) {
            dao.saveLaunch(launchDetail1)
            dao.saveLaunch(launchDetail2)

            val savedLaunches = dao.getSavedLaunches()
            val result = savedLaunches.firstOrNull()
            val expected = listOf(launchDetail1, launchDetail2)

            assertThat(result).isEqualTo(expected)
        }
    }

    private fun getLaunchDetailDtoFromJson(jsonString: String): LaunchDetailDto? {
        val adapter: JsonAdapter<LaunchDetailDto> =
            moshi.adapter(LaunchDetailDto::class.java)

        return adapter.fromJson(jsonString)
    }
}
