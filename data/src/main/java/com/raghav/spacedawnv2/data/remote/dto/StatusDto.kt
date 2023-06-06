package com.raghav.spacedawnv2.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.raghav.spacedawnv2.domain.model.Status

@Keep
data class StatusDto(
    @SerializedName("abbrev")
    val abbrev: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)

fun StatusDto.toStatus(): Status {
    return Status(abbrev, description, id, name)
}
