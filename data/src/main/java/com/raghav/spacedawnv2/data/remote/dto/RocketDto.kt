package com.raghav.spacedawnv2.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.raghav.spacedawnv2.domain.model.Rocket

@Keep
data class RocketDto(
    @SerializedName("configuration")
    val configuration: ConfigurationDto?,
    @SerializedName("id")
    val id: Int?
)

fun RocketDto.toRocket(): Rocket {
    return Rocket(
        configuration = this.configuration?.toConfiguration(),
        id = this.id
    )
}
