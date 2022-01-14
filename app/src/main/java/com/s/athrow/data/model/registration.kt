package com.s.athrow.data.model

import androidx.annotation.DrawableRes

data class registration(
   @DrawableRes val coverResId: Int,
    val description: String,
    val password: Int,
    val name: String
)
