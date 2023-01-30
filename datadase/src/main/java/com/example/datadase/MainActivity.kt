package com.example.datadase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.datadase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = openOrCreateDatabase("testdb", Context.MODE_PRIVATE, null) //데이터베이스 객체 생성

        //테이블 생성(create 문)
        db.execSQL("create table USER_TB(" +
                "_id integer primary key autoincrement," +
                "name not null," +
                "phone)")

        //데이터 삽입(insert 문)
        db.execSQL("insert into USER_TB(name, phone) values (?,?)",
            arrayOf<String>("HamTory","010-4607-0741"))

        //데이터 조회(select 문)
        val cursor = db.rawQuery("select * from USER_TB", null)
    }
}