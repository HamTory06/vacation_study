package com.example.datadase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
//name : DB파일명, version: DB 버전 정보
class DBHelper(context: Context): SQLiteOpenHelper(context, "testdb_1", null, 1) {
    //테이블을 생성하거나 스키마를 변경하는 코드는 SQLiteOpenHelper에 작성
    override fun onCreate(db: SQLiteDatabase?) {
        //앱이 설치된 후 SQLiteOpenHelper 클래스가 이용되는 순간 한번 호출한다
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //생성자가 지정한 DB 버전 정보가 변경괼 때 마다 호출한다
    }
}