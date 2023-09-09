package com.raghav.spacedawnv2.data.util

import com.raghav.spacedawnv2.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * A generic function that fetches data from a network source and stores it in a local database,
 * all while observing the provided query function for local data.
 *
 * @param query A function that returns a Flow representing the locally cached data.
 * @param fetch A suspend function that fetches data from the network source.
 * @param saveFetchedResult A suspend function that saves the fetched data into the local database.
 * @param shouldFetch A function that determines whether the network should be queried based on some condition.
 * Defaults to true, meaning the network call will be made if not specified otherwise.
 * @return A Flow emitting the resource result.
 *
 * @see ResultType The type of data stored in the local database.
 * @see RequestType The type of data fetched from the network source.
 */
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchedResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: () -> Boolean = { true }
) = flow {
    val flow = if (shouldFetch()) {
        try {
            saveFetchedResult(fetch())
            query().map { Resource.Success(it) }
        } catch (e: Exception) {
            query().map { Resource.Error(e.localizedMessage.orEmpty(), it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}
