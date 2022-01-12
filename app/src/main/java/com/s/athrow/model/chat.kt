package model

import androidx.annotation.DrawableRes

data class chat(
    @DrawableRes
    val im_modelID: Int,
    val massage: String,
    val answer: String
)
