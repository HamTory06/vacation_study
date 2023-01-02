package com.example.ch6_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import com.example.ch6_view.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean { //MotionEvent는 터치의 종류와 발생 지점을 남긴다
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        when (event?.action) {
            ACTION_DOWN -> {
                Log.d("TouchEvent","down")
                Log.d("TouchEvent","""
                    Touch Event x: ${event.x}
                    Touch Event y: ${event.y}
                """.trimMargin())
            }
            MotionEvent.ACTION_UP -> {
                Log.d("TouchEvent","UP")
            }
        }
        return super.onTouchEvent(event)
    }
}