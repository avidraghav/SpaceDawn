package com.raghav.spacedawnv2.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.raghav.spacedawnv2.domain.model.Configuration

@Keep
data class ConfigurationDto(
    @SerializedName("family")
    val family: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("variant")
    val variant: String?
)

fun ConfigurationDto.toConfiguration(): Configuration {
    return Configuration(
        family = this.family,
        full_name = this.fullName,
        id = this.id,
        name = this.name,
        url = this.url,
        variant = this.variant
    )
}
