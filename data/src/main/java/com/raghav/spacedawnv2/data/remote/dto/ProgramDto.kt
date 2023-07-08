package com.raghav.spacedawnv2.data.remote.dto

import com.raghav.spacedawnv2.domain.model.Program
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// @JsonClass is used to make sure that Moshi uses code-gen instead of Reflection
// for Serializing and Deserializing data
@JsonClass(generateAdapter = true)
data class ProgramDto(
    @Json(name = "agencies")
    val agencies: List<AgencyDto?>?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "end_date")
    val endDate: Any?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image_url")
    val imageUrl: String?,
    @Json(name = "info_url")
    val infoUrl: String?,
    @Json(name = "mission_patches")
    val missionPatches: List<Any?>?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "start_date")
    val startDate: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "wiki_url")
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
