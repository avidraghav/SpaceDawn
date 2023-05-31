package com.raghav.spacedawnv2.domain.util

/**
 * A generic class that contains the state after performing an action
 * whether that action was successful or failure. In case of success the action
 * can be represented by Resource.Success and in case of Failure
 * Resource.Error
 *
 * @param data data obtained after performing the said action
 * @param message error message in case when the action gets failed
 * */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
