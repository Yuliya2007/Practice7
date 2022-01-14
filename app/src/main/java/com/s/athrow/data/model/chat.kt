package com.s.athrow.data.model

import androidx.annotation.DrawableRes

data class chat(
    @DrawableRes val coverResId: Int,
    val massage: String,
    val answer: String
)
