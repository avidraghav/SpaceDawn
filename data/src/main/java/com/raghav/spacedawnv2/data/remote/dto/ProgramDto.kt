package com.raghav.spacedawnv2.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.collections.immutable.ImmutableList

// @JsonClass is used to make sure that Moshi uses code-gen instead of Reflection
// for Serializing and Deserializing data
@JsonClass(generateAdapter = true)
data class ProgramDto(
    @Json(name = "agencies")
    val agencies: ImmutableList<AgencyDto?>?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "end_date")
    val endDate: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image_url")
    val imageUrl: String?,
    @Json(name = "info_url")
    val infoUrl: String?,
    @Json(name = "mission_patches")
    val missionPatches: ImmutableList<String?>?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "start_date")
    val startDate: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "wiki_url")
    val wikiUrl: String?
)
