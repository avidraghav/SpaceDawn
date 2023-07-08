package com.raghav.spacedawnv2.data.remote.dto

import androidx.annotation.Keep
import com.raghav.spacedawnv2.domain.model.Agency
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// @JsonClass is used to make sure that Moshi uses code-gen instead of Reflection
// for Serializing and Deserializing data
@JsonClass(generateAdapter = true)
@Keep
data class AgencyDto(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "url")
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
