package com.raghav.spacedawnv2.data.di

import BaseUrlProvider
import com.raghav.spacedawnv2.data.remote.LaunchesApi
import com.raghav.spacedawnv2.domain.util.NetworkInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(interceptor: NetworkInterceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        val level = logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .addInterceptor(level)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideLaunchesApi(client: OkHttpClient, moshi: Moshi): LaunchesApi {
        return Retrofit.Builder()
            .baseUrl(BaseUrlProvider.BASE_URL_LAUNCHES_API)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build().create(LaunchesApi::class.java)
    }
}
