package com.raghav.spacedawnv2.data.repository

import com.google.common.truth.Truth.assertThat
import com.raghav.spacedawnv2.data.local.LaunchesDao
import com.raghav.spacedawnv2.data.remote.LaunchesApi
import com.raghav.spacedawnv2.data.remote.dto.LaunchesResponseDto
import com.raghav.spacedawnv2.data.remote.dto.toDomain
import com.raghav.spacedawnv2.data.util.launchesResponseDtoString
import com.raghav.spacedawnv2.domain.repository.LaunchesRepository
import com.raghav.spacedawnv2.domain.util.Resource
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
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

    private val moshi = Moshi.Builder().build()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        launchesRepository = LaunchesRepositoryImpl(mockApi, mockDao)
    }

    @Test
    fun `getLaunches()_ifRequestWasSuccessful_ReturnsSuccess(LaunchesResponse) `() = runTest {
        val dtoObject = getDtoFromJson<LaunchesResponseDto>(launchesResponseDtoString)
        Mockito.`when`(mockApi.getLaunches()).thenReturn(
            Response.success(dtoObject)
        )

        val requestResult = mockApi.getLaunches()

        if (requestResult.isSuccessful) {
            val result = Resource.Success(requestResult.body()?.toDomain())
            val expected = Resource.Success(dtoObject?.toDomain())
            assertThat(expected.data).isEqualTo(result.data)
            assertThat(result).isInstanceOf(Resource.Success::class.java)
        }
    }

    // error_message = "Requests Limit Reached, please try after 1 hour"

    @Test
    fun `getLaunches() returns Error(error_message) if status code was 429`() =
        runTest {
            Mockito.`when`(mockApi.getLaunches()).thenReturn(
                Response.error(429, "".toResponseBody(null))
            )
            val requestResult = mockApi.getLaunches()

            if (requestResult.isSuccessful.not()) {
                val expectedCode = 429
                val resultCode = requestResult.code()
                assertThat(expectedCode).isEqualTo(resultCode)
            }
        }

    // error_message = "Some Unknown Error Occurred"

    @Test
    fun `getLaunches() returns Error(error_message) if request was unsuccessful with any status code`() =
        runTest {
            Mockito.`when`(mockApi.getLaunches()).thenReturn(
                Response.error(404, "".toResponseBody(null))
            )
            val requestResult = mockApi.getLaunches()

            if (requestResult.isSuccessful.not()) {
                val resultCode = requestResult.code()
                assertThat(resultCode).isNotEqualTo(429)
            }
        }

    private inline fun <reified T> getDtoFromJson(jsonString: String): T? {
        val adapter: JsonAdapter<T> =
            moshi.adapter(T::class.java)

        return adapter.fromJson(jsonString)
    }
}
