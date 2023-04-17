package com.example.roomstudy.Room.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomstudy.Room.Entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(text: String)

    @Query(value = "select * from USER")
    fun getAllUser():List<UserEntity>

    @Update(UserEntity::class, onConflict = OnConflictStrategy.ABORT)
    fun update(text: String, new: String)

    @Delete(UserEntity::class)
    fun delete(text: String)
}