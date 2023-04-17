package com.example.roomstudy.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "profile",
    primaryKeys = ["profile_idx"],
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["user_idx"],
        childColumns = ["user_idx"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)]
)
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "profile_idx")
    val profileIdx: Int,

    @ColumnInfo(name = "user_idx")
    val userIdx: Int,

    @ColumnInfo(name = "cell_phone")
    val cellPhone: String,

    @ColumnInfo(name ="address")
    val address: String,

    @ColumnInfo(name ="email")
    val email: String
)
