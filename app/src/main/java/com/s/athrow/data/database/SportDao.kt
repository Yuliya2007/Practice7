package com.s.athrow.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.s.athrow.data.model.Information

@Dao
interface SportDao {
    @Query("SELECT * FROM Information")
    suspend fun getAll(): List<Information>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<Information>)
}