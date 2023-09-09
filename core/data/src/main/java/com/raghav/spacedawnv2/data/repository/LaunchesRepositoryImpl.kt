package com.raghav.spacedawnv2.data.repository

import com.raghav.spacedawnv2.data.local.LaunchesDao
import com.raghav.spacedawnv2.data.remote.LaunchesApi
import com.raghav.spacedawnv2.data.remote.dto.toLaunchDetail
import com.raghav.spacedawnv2.data.util.NetworkConnectivityManager
import com.raghav.spacedawnv2.data.util.networkBoundResource
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.model.Reminder
import com.raghav.spacedawnv2.domain.repository.LaunchesRepository
import com.raghav.spacedawnv2.domain.util.Resource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class LaunchesRepositoryImpl @Inject constructor(
    private val launchesApi: LaunchesApi,
    private val launchesDao: LaunchesDao,
    private val connectivityManager: NetworkConnectivityManager
) : LaunchesRepository {

    override fun getLaunches(): Flow<Resource<List<LaunchDetail>>> {
        return networkBoundResource(
            query = { launchesDao.getLaunches() },
            fetch = { launchesApi.getLaunches() },
            saveFetchedResult = { response ->
                val launches = response.body()?.results?.filterNotNull() ?: emptyList()
                launchesDao.cacheLaunches(launches.map { it.toLaunchDetail() })
            },
            shouldFetch = { connectivityManager.isConnectedToNetwork() }
        )
    }

    override suspend fun saveReminder(reminder: Reminder) {
        launchesDao.saveReminder(reminder)
    }
}
