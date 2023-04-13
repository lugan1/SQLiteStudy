package com.example.roomstudy

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)

        binding.insertButton.setOnClickListener {
            val value = binding.inputArea.text.toString()
            dbHelper.insert(value) //insert문 수행
        }

        binding.updateButton.setOnClickListener {
            val value = binding.inputArea.text.toString()
            dbHelper.update(value) //update문 수행
        }

        binding.selectButton.setOnClickListener {
            val result = dbHelper.select() //select문 수행
            print(result)
        }

        binding.deleteButton.setOnClickListener {
            val value = binding.inputArea.text.toString()
            dbHelper.delete(value) //delete문 수행
        }
    }
}