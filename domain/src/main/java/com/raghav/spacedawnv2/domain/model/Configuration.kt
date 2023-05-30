package com.raghav.spacedawnv2.domain.model

import androidx.annotation.Keep

@Keep
data class Configuration(
    val family: String?,
    val full_name: String?,
    val id: Int?,
    val name: String?,
    val url: String?,
    val variant: String?
)
