package com.example.roomstudy.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context?): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "myTestDB.db"
        private const val TABLE_NAME ="my_table"

        //테이블 컬럼
        private const val ID = "id" //order: 0
        private const val TITLE = "title" //order: 1

        private const val TEST = "TEST_LOG"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //테이블 생성 쿼리 작성 my_table(ID, TITLE)
        val CREATE_TABLE = "CREATE TABLE if not exists $TABLE_NAME (" +
                "$ID INTEGER PRIMARY KEY," +
                "$TITLE TEXT);"

        db?.let {
            val result = it.execSQL(CREATE_TABLE) //쿼리 실행
            if(it.isOpen){ //DB가 열려있으면
                //it.close() //DB를 닫는다.
            }
            Log.d(TEST, result.toString())
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //새 버전의 DB가 존재하면, 기존의 테이블은 삭제한다.
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db) //다시 새 테이블을 생성한다.
    }

    fun insert(title: String){
        //쓰기모드로 데이터베이스를 가져온다.
        val db = this.writableDatabase

        //테이블에 Insert할 값을 설정한다. put(컬럼이름, 데이터)
        val values = ContentValues().apply { put(TITLE, title) }

        val result = db.insert(TABLE_NAME, null, values)
        //db.close() //db를 닫는다.
        Log.d(TEST, result.toString())
    }

    fun select(): ArrayList<String> {
        //읽기모드로 데이터베이스를 가져온다.
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"

        val cursor = db.rawQuery(query, null) //모든 값이 반환되지는 않고, 커서 형태로 값이 반환된다.
        //db.close() //db를 닫는다.
        val result = ArrayList<String>()

        //컬럼0: ID, 컬럼1: Title
        with(cursor) {
            while (moveToNext()){ //Cursor를 움직이면서 값을 하나씩 빼와야 한다.
                result.add(getString(1)) //컬럼1: Title 의 값만 계속 빼와서 result에 넣는다.
            }
        }

        return result
    }

    fun update(title: String, update: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply { put(TITLE, update) } //업데이트할 컬럼과 값을 설정한다.

        val whereClause = "$TITLE LIKE ?" //쿼리의 Where 절을 설정한다.
        val whereArgs = arrayOf(title) //Where 절의 ?에 들어갈 args 들

        val count = db.update(TABLE_NAME, values, whereClause, whereArgs)
        //db.close() //db를 닫는다.
        Log.d(TEST, count.toString())
    }

    fun delete(title: String) {
        val db = this.writableDatabase

        val whereClause = "$TITLE LIKE ?" //쿼리의 Where 절을 설정한다.
        val whereArgs = arrayOf<String>(title) //Where 절의 ?에 들어갈 args 들

        //테이블의 해당 값을 삭제한다.
        val count = db.delete(TABLE_NAME, whereClause, whereArgs)
        //db.close() //db를 닫는다.
        Log.d(TEST, count.toString())
    }
}