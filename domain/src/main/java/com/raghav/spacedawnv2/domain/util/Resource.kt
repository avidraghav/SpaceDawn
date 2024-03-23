package com.raghav.spacedawnv2.domain.util

/**
 * A generic class that contains the state after performing an action
 * whether that action was successful or failure. In case of success the action
 * can be represented by Resource.Success and in case of Failure
 * Resource.Error
 *
 * @param data data obtained after performing the said action
 * @param errorMessage error message in case when the action gets failed
 * @param exception exception in case when the action gets failed
 * */
sealed class Resource<T>(
    val data: T? = null,
    val errorMessage: String? = null,
    val exception: Exception? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String? = null, data: T? = null, exception: Exception? = null) :
        Resource<T>(data = data, errorMessage = message, exception = exception)
}
