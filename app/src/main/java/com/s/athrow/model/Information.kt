package model

import androidx.annotation.DrawableRes

data class Information(
    @DrawableRes
    val im_modelID: Int,
    val kind_of_sport: String,
    val description: String
)
