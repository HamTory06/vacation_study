package com.example.datadase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.datadase.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db_1 = openOrCreateDatabase("testdb_1", Context.MODE_PRIVATE, null) //데이터베이스 객체 생성
//        val db_2 = openOrCreateDatabase("testdb_2", Context.MODE_PRIVATE, null)
//        val db: SQLiteDatabase = DBHelper(this).writableDatabase //베이터 베이스 객체 생성()
        //테이블 생성(create 문)
        db_1.execSQL("create table USER_TB (" +
                "_id integer primary key autoincrement," +
                "name not null," +
                "phone)")

        //데이터 삽입(insert 문)
        db_1.execSQL("insert into USER_TB(name, phone) values (?,?)",
            arrayOf<String>("HamTory", "010-4607-0741"))

        //데이터 조회(select 문)
        val cursor_1 = db_1.rawQuery("select * from USER_TB", null)

        //선택한 행의 값 가져오기
        while(cursor_1.moveToNext()){
            val name = cursor_1.getString(0)
            val phone = cursor_1.getString(1)
        }
        //insert()함수 사용
//        val values = ContentValues()
//        values.put("name","HamTory")
//        values.put("phone","010-4607-0741")
//        db_2.insert("USER_TB", null, values)

        //query() 함수 사용
//        val cursor_2 = db_2.query("USER_TB", arrayOf<String>("name","phone"), "phone=?", arrayOf<String>("010-4607-0741"), null, null, null)



        val file = File(filesDir, "test1.txt") //파일 객체 생성
        val writeStream: OutputStreamWriter = file.writer()
        writeStream.write("hello world") //데이터 쓰기
        writeStream.flush() //끝

        val readStream: BufferedReader = file.reader().buffered()
        readStream.forEachLine {
            Log.d("상태","$it") //파일 데이터 읽기
        }


        openFileOutput("test2.txt", Context.MODE_PRIVATE).use {
            it.write("hello world".toByteArray())
        }
        openFileInput("test2.txt").bufferedReader().forEachLine {
            Log.d("상태","$it")
        }
    }
}