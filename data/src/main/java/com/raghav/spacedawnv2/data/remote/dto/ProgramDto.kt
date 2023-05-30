package com.raghav.spacedawnv2.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.raghav.spacedawnv2.domain.model.Program

@Keep
data class ProgramDto(
    @SerializedName("agencies")
    val agencies: List<AgencyDto?>?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("end_date")
    val endDate: Any?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("info_url")
    val infoUrl: String?,
    @SerializedName("mission_patches")
    val missionPatches: List<Any?>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("start_date")
    val startDate: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("wiki_url")
    val wikiUrl: String?
)

fun ProgramDto.toProgram(): Program {
    return Program(
        agencies = agencies?.map { it?.toAgency() },
        description = description,
        end_date = endDate,
        id = id,
        image_url = imageUrl,
        info_url = infoUrl,
        mission_patches = missionPatches,
        name = name,
        start_date = startDate,
        url = url,
        wiki_url = wikiUrl
    )
}
