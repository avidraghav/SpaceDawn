package com.raghav.spacedawnv2.util

import android.content.Context
import com.raghav.spacedawnv2.domain.util.NetworkInterceptor
import java.io.BufferedReader
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * Default Network Interceptor in case of Debug build. Use it to mock Api responses or
 * testing purposes.
 * see https://avidraghav.hashnode.dev/intercepting-network-requests-in-android-via-okhttp-interceptor
 */
class FakeInterceptor(private val context: Context) : NetworkInterceptor {
    private var contentType = "application/json"

    override fun intercept(chain: Interceptor.Chain): Response {
        // val uri = chain.request().url.toUrl()

//        when {
//            uri.toString()
//                .contains("https://lldev.thespacedevs.com/2.2.0/") -> {
//                return getResponse(chain, R.raw.mock_response_upcoming_launches)
//            }
//        }
        return chain.proceed(chain.request())
    }

    private fun getResponse(chain: Interceptor.Chain, resId: Int): Response {
        val jsonString = this.context.resources
            .openRawResource(resId)
            .bufferedReader()
            .use(BufferedReader::readText)

        val builder = Response.Builder()
        builder.request(chain.request())
        builder.protocol(Protocol.HTTP_2)
        builder.addHeader("content-type", contentType)
        builder.body(
            jsonString.toByteArray().toResponseBody(contentType.toMediaTypeOrNull())
        )
        builder.code(200)
        builder.message(jsonString)
        return builder.build()
    }
}
