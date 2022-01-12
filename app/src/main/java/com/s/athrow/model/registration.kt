package model

import android.icu.text.CaseMap
import androidx.annotation.DrawableRes

data class registration(
    @DrawableRes
    val im_modelID: Int,
    val description: String,
    val password: Int,
    val name: String
)
