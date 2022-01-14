package com.s.athrow.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Information(
    val title: String,
    val image: String,
    val description: String
)
