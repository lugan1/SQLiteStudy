package com.example.roomstudy.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "text_table")
data class TextEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idx")
    val idx: Int = 0,

    @ColumnInfo(name = "text")
    val text: String = ""
)
