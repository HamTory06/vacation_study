package com.example.intent

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.intent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent: Intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("data1","Hello")
        intent.putExtra("data2","World")
        intent.putExtra("data3",10)
        startActivity(intent)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { //결과를 돌려받은 후 처리
//        super.onActivityResult(requestCode, resultCode, data)
//        if(requestCode == 10 && resultCode == Activity.RESULT_OK){
//            val result = data?.getStringExtra("resultData")
//            if (result != null) {
//                Log.d("MainActivity상태",result)
//            }
//        }
//    }
}