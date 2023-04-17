package com.example.roomstudy

import android.os.Bundle
import android.provider.Settings.Global
import androidx.appcompat.app.AppCompatActivity
import com.example.roomstudy.Room.Dao.TextDao
import com.example.roomstudy.Room.Database.TextDatabase
import com.example.roomstudy.Room.Entity.TextEntity
import com.example.roomstudy.SQLite.DBHelper
import com.example.roomstudy.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private lateinit var roomDB: TextDatabase
    private lateinit var textDao: TextDao
    private lateinit var binding: ActivityMainBinding

    @OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)
        roomDB = TextDatabase.getDatabase(this)
        textDao = roomDB.textDao()


        binding.insertButton.setOnClickListener {
            val value = binding.inputArea.text.toString()
            dbHelper.insert(value) //insert문 수행
            dbHelper.close()
        }

        binding.updateButton.setOnClickListener {
            val value = binding.inputArea.text.toString()
            val update = binding.inputArea2.text.toString()
            dbHelper.update(value, update) //update문 수행
            dbHelper.close()
        }

        binding.selectButton.setOnClickListener {
            val result = dbHelper.select() //select문 수행
            binding.textView.text = result.toString()
            dbHelper.close()
        }

        binding.deleteButton.setOnClickListener {
            val value = binding.inputArea.text.toString()
            dbHelper.delete(value) //delete문 수행
            dbHelper.close()
        }

        binding.roomInsert.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val text = binding.inputArea.text.toString()
                textDao.insert(TextEntity(text = text))
            }
        }

        binding.roomSelect.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val result = textDao.getAllData()

                launch(Dispatchers.Main) {
                    binding.textView.text = result.toString()
                }
            }
        }

        binding.roomUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val pk = binding.inputArea.text.toString().toInt()
                val new = binding.inputArea2.text.toString()
                textDao.update(TextEntity(pk, new))
            }
        }

        binding.roomDelete.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val pk = binding.inputArea.text.toString().toInt()
                textDao.delete(TextEntity(idx = pk))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}