package com.example.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.intent.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getStringExtra("data2")
        val data3 = intent.getIntExtra("data3",0)
        //startActivityForResult(intent,0)//두 번째 매개변수는 개발자가 정하는 요청코드(requestCode)이며 인텐트를 식별하는 값이다(startAcrivityForResult()함수로 결과를 돌려받 후 별도로 처리할 때 필요하다),안드로이드 버전 11부터 ActivityResultLauncher를 사용하라 권장함
        intent.putExtra("resultData","world")
//        setResult(RESULT_OK,intent)
//        finish()//finish()함수는 현재 화면에 보이는 액티비티를 종료해 달라고 시스템에 요청
    }
}