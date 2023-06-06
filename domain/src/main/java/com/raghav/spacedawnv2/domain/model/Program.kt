package com.raghav.spacedawnv2.domain.model

import androidx.annotation.Keep

@Keep
data class Program(
    val agencies: List<Agency?>?,
    val description: String?,
    val end_date: Any?,
    val id: Int?,
    val image_url: String?,
    val info_url: String?,
    val mission_patches: List<Any?>?,
    val name: String?,
    val start_date: String?,
    val url: String?,
    val wiki_url: String?
)
