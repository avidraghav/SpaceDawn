package com.raghav.spacedawnv2.domain.util

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Interface for providing Coroutine Dispatchers
 * in main and test source set
 */
interface DispatchersProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}
