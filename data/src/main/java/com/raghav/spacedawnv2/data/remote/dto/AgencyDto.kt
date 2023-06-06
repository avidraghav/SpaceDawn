package com.raghav.spacedawnv2.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.raghav.spacedawnv2.domain.model.Agency

@Keep
data class AgencyDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)

fun AgencyDto.toAgency(): Agency {
    return Agency(
        id = this.id,
        name = this.name,
        type = this.type,
        url = this.url
    )
}
