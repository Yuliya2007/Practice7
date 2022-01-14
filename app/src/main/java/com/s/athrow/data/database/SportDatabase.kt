package com.s.athrow.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.s.athrow.data.model.Information

@Database(
    entities = [Information::class],
    version = 1
)

abstract class SportDatabase : RoomDatabase() {
    abstract fun sportDao(): SportDao
}