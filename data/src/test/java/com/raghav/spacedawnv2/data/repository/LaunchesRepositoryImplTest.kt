package com.raghav.spacedawnv2.data.repository

import com.google.common.truth.Truth.assertThat
import com.raghav.spacedawnv2.data.local.LaunchesDao
import com.raghav.spacedawnv2.data.remote.LaunchesApi
import com.raghav.spacedawnv2.data.remote.dto.LaunchesResponseDto
import com.raghav.spacedawnv2.data.remote.dto.toLaunchDetail
import com.raghav.spacedawnv2.data.util.NetworkConnectivityManager
import com.raghav.spacedawnv2.data.util.launchesResponseDtoString
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.repository.LaunchesRepository
import com.raghav.spacedawnv2.domain.util.Resource
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response

/**
 * Contains Unit Test cases for verifying the
 * working/integrity of [LaunchesRepositoryImpl]
 *
 * Test cases follow naming convention:
 * unitUnderTest_inputProvidedToUnit_resultExpected
 */

class LaunchesRepositoryImplTest {

    private lateinit var launchesRepository: LaunchesRepository

    @Mock
    private lateinit var mockDao: LaunchesDao

    @Mock
    private lateinit var mockApi: LaunchesApi

    @Mock
    private lateinit var mockConnectivityManager: NetworkConnectivityManager

    private val moshi = Moshi.Builder().build()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        launchesRepository = LaunchesRepositoryImpl(mockApi, mockDao, mockConnectivityManager)
    }

    @Test
    fun `getLaunches() returns Flow containing cached Launches after refreshing the cache if Internet Available`() =
        runTest {
            val dtoObject = getDtoFromJson<LaunchesResponseDto>(launchesResponseDtoString)
            val launches =
                dtoObject?.results?.filterNotNull()?.map { it.toLaunchDetail() } ?: emptyList()

            Mockito.`when`(mockApi.getLaunches()).thenReturn(
                Response.success(dtoObject)
            )
            Mockito.`when`(mockConnectivityManager.isConnectedToNetwork()).thenReturn(
                true
            )
            Mockito.`when`(mockDao.getLaunches()).thenReturn(
                flowOf(launches)
            )

            val cachedLaunches = launchesRepository.getLaunches()

            assertThat(cachedLaunches.first()).isInstanceOf(Resource.Success::class.java)
            assertThat(cachedLaunches.first().data).isEqualTo(launches)
        }

    @Test
    fun `getLaunches() returns Flow containing only error message if no cache available in case of Failure while refreshing cache`() =
        runTest {
            Mockito.`when`(mockDao.getLaunches()).thenReturn(
                flowOf(emptyList())
            )
            Mockito.`when`(mockApi.getLaunches()).thenThrow(
                HttpException(Response.error<LaunchesResponseDto>(400, "".toResponseBody(null)))
            )
            Mockito.`when`(mockConnectivityManager.isConnectedToNetwork()).thenReturn(
                true
            )

            val cachedLaunches = launchesRepository.getLaunches()
            assertThat(cachedLaunches.first()).isInstanceOf(Resource.Error::class.java)
        }

    @Test
    fun `getLaunches() returns Flow containing error message along with cache if cache available in case of Failure while refreshing cache`() =
        runTest {
            val dtoObject = getDtoFromJson<LaunchesResponseDto>(launchesResponseDtoString)
            val launches =
                dtoObject?.results?.filterNotNull()?.map { it.toLaunchDetail() } ?: emptyList()

            Mockito.`when`(mockDao.getLaunches()).thenReturn(
                flowOf(launches)
            )
            Mockito.`when`(mockApi.getLaunches()).thenThrow(
                HttpException(Response.error<LaunchesResponseDto>(400, "".toResponseBody(null)))
            )
            Mockito.`when`(mockConnectivityManager.isConnectedToNetwork()).thenReturn(
                true
            )

            val cachedLaunches = launchesRepository.getLaunches()
            assertThat(cachedLaunches.first()).isInstanceOf(Resource.Error::class.java)
            assertThat(cachedLaunches.first().data).isEqualTo(launches)
        }

    @Test
    fun `getLaunches() returns Flow containing empty List if there is no Internet along with cache`() =
        runTest {
            Mockito.`when`(mockDao.getLaunches()).thenReturn(
                flowOf(emptyList())
            )
            Mockito.`when`(mockConnectivityManager.isConnectedToNetwork()).thenReturn(
                false
            )

            val cachedLaunches = launchesRepository.getLaunches()
            assertThat(cachedLaunches.first().data).isEqualTo(emptyList<LaunchDetail>())
            assertThat(cachedLaunches.first()).isInstanceOf(Resource.Success::class.java)
        }

    private inline fun <reified T> getDtoFromJson(jsonString: String): T? {
        val adapter: JsonAdapter<T> =
            moshi.adapter(T::class.java)

        return adapter.fromJson(jsonString)
    }
}
