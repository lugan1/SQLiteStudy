package com.example.roomstudy.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "biometric",
    primaryKeys = ["biometric_idx"],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["user_idx"],
            childColumns = ["user_idx"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )])
data class BiometricEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "biometric_idx")
    val biometricIdx: Int,

    @ColumnInfo(name = "user_idx")
    val userIdx: Int,

    @ColumnInfo(name = "log_idx")
    val logIdx: Int,

    @ColumnInfo(name = "activity")
    val activity: String,

    @ColumnInfo(name = "scd")
    val scd: String,

    @ColumnInfo(name = "temperature")
    val temperature: Double,

    @ColumnInfo(name = "spO2")
    val spO2: Int,

    @ColumnInfo(name = "respiratory")
    val respiratory: Int,

    @ColumnInfo(name = "rmssd")
    val rmssd: Int,

    @ColumnInfo(name = "step")
    val step: Int,

    @ColumnInfo(name = "measurement_Date", defaultValue = "CURRENT_TIMESTAMP")
    val measurementDate: LocalDateTime,

    @ColumnInfo(name ="create_date", defaultValue = "CURRENT_TIMESTAMP")
    val createDate: LocalDateTime
)
