package com.example.roomstudy.Room.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomstudy.Room.Entity.TextEntity

@Dao
interface TextDao {
    @Query("SELECT * FROM text_table")
    fun getAllData(): List<TextEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(text: TextEntity)

    @Delete
    fun delete(text: TextEntity)

    @Update
    fun update(text: TextEntity)
}