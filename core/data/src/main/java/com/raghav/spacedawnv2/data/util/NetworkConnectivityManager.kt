package com.raghav.spacedawnv2.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A singleton class responsible for checking the availability of the Internet connection.
 *
 * @param context The application context used to access system services.
 *
 * @constructor Creates an instance of [NetworkConnectivityManager].
 */
@Singleton
class NetworkConnectivityManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    /**
     * Returns true if the Internet Connection (both cellular or wifi)
     * is available otherwise false
     * */
    fun isConnectedToNetwork(): Boolean {
        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Returns a Network object corresponding to
        // the currently active default data network.
        val network = connectivityManager.activeNetwork ?: return false

        // Representation of the capabilities of an active network.
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            // Indicates this network uses a Wi-Fi transport,
            // or WiFi has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            // Indicates this network uses a Cellular transport. or
            // Cellular has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            // else return false
            else -> false
        }
    }
}
