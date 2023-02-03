package com.example.preferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.preferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding? = null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref_1 = getPreferences(Context.MODE_PRIVATE) //액티비티의 데이터 저장
        val sharedPref_2 = getSharedPreferences("my_prefs", Context.MODE_PRIVATE) //앱 전체의 데이터 저장

        sharedPref_1.edit().run {
            putString("data1","hello") //(키값, 저장될값)
            putInt("data2",10) //(키값, 저장될값)
            commit()
        }

        val data1 = sharedPref_1.getString("data1","world")
        val data2 = sharedPref_2.getInt("data2",10)

        Log.d("상태","data1 : $data1 data2 : $data2")
    }
}