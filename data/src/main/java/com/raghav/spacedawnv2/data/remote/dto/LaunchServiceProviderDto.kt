package com.raghav.spacedawnv2.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.raghav.spacedawnv2.domain.model.LaunchServiceProvider

@Keep
data class LaunchServiceProviderDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)

fun LaunchServiceProviderDto.toLaunchServiceProvider(): LaunchServiceProvider {
    return LaunchServiceProvider(
        id = id,
        name = name,
        type = type,
        url = url
    )
}
