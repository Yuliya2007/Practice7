package com.s.athrow.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.serialization.Serializable

@Serializable
@Entity(primaryKeys = ["title","image","description"])
data class Information(
    @ColumnInfo val title: String,
    @ColumnInfo val image: String,
    @ColumnInfo val description: String
)
