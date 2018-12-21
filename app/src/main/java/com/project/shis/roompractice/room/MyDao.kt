package com.project.shis.roompractice.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface MyDao {
    @Query("SELECT * FROM cat")
    fun getAll():List<Cat>

    @Insert(onConflict = REPLACE)
    fun insert(cat :Cat)

    @Query("DELETE FROM cat")
    fun deleteAll()
}