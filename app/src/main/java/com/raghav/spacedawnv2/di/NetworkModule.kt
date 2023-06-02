package com.raghav.spacedawnv2.di

import BaseUrlProvider
import com.raghav.spacedawnv2.data.remote.LaunchesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        val level = logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(level).build()
    }

    @Provides
    @Singleton
    fun provideLaunchesApi(client: OkHttpClient): LaunchesApi {
        return Retrofit.Builder()
            .baseUrl(BaseUrlProvider.BASE_URL_LAUNCHES_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(LaunchesApi::class.java)
    }
}
