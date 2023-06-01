package com.raghav.spacedawnv2.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@ExperimentalCoroutinesApi
class TestDispatchers : DispatcherProvider {
    private val dispatcher = UnconfinedTestDispatcher()

    override val main: CoroutineDispatcher
        get() = dispatcher
}
