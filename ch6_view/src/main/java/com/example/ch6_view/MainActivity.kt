package com.example.ch6_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean { //MotionEvent는 터치의 종류와 발생 지점을 남긴다
        return super.onTouchEvent(event)
        when (event?.action) {
            MotionEvent.ACTION_DOWN{
                Log.d("touch Event")
            }
        }
    }
}