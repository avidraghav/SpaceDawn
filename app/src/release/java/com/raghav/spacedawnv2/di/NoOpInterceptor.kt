package com.raghav.spacedawnv2.di

import com.raghav.spacedawnv2.domain.util.NetworkInterceptor
import okhttp3.Interceptor
import okhttp3.Response

class NoOpInterceptor : NetworkInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // This interceptor does nothing and immediately returns the original response
        return chain.proceed(chain.request())
    }
 }
