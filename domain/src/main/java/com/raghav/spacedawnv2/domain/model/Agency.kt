package com.raghav.spacedawnv2.domain.model

import androidx.annotation.Keep

@Keep
data class Agency(
    val id: Int?,
    val name: String?,
    val type: String?,
    val url: String?
)
