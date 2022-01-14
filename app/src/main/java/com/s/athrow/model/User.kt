package com.s.athrow.model

import androidx.annotation.DrawableRes

data class User(
    val title: String,
    @DrawableRes
    val coverResId: Int
)
