package com.example.roomstudy.Room.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomstudy.Room.Dao.TextDao
import com.example.roomstudy.Room.Entity.TextEntity

@Database(entities = [TextEntity::class], version = 1)
abstract class TextDatabase : RoomDatabase() {
    abstract fun textDao(): TextDao

    companion object {
        @Volatile
        private var INSTANCE: TextDatabase? = null

        fun getDatabase(
            context: Context
        ) : TextDatabase {
            // 만약 INSTANCE 가 있다면, INSTANCE 를 리턴하고, 없으면 Room.databaseBuilder 로 데이터베이스를 생성하고 리턴한다.
            // 싱글톤 패턴인듯
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TextDatabase::class.java,
                    "text_database"
                ).fallbackToDestructiveMigration() //데이터 베이스 버전이 변경되면, 마이그레이션을 해야되는데, 마이그레이션 실패시 IllegalException 보다는 DB를 삭제후 재생성한다.
                 .build()

                INSTANCE = instance
                instance
            }
        }
    }
}