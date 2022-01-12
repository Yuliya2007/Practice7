package com.s.athrow.model

import androidx.annotation.DrawableRes

data class Information(
    val titel: String,
    @DrawableRes val coverResId: Int,
    val description: String
)
