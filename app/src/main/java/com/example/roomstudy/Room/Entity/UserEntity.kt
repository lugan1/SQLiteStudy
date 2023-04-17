package com.example.roomstudy.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "user",
    primaryKeys = ["user_idx"],
    indices = [Index(
        value = ["id"],
        unique = true)]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_idx")
    val userIdx: Int,

    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "create_date", defaultValue = "CURRENT_TIMESTAMP")
    val createDate: LocalDateTime,

    @Ignore
    var test: String
)
