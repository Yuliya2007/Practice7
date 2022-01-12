package com.s.athrow.model

import androidx.annotation.DrawableRes

data class news(
    val title: String,
    @DrawableRes val coverResId: Int,
    val description: String
)

