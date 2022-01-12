package com.s.athrow.model

import androidx.annotation.DrawableRes

data class chat(
    @DrawableRes val coverResId: Int,
    val massage: String,
    val answer: String
)
