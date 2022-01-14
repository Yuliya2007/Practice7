package com.s.athrow.data.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var db:  SportDatabase? = null
    fun provideDatabase(context: Context):  SportDatabase {
        return db ?: Room.databaseBuilder(
            context.applicationContext,
           SportDatabase::class.java, "database"
        ).build().also { db = it }
    }
}