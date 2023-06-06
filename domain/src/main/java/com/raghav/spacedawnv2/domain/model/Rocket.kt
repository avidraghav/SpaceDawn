package com.raghav.spacedawnv2.domain.model

import androidx.annotation.Keep

@Keep
data class Rocket(
    val configuration: Configuration?,
    val id: Int?
)
