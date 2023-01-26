package com.example.receiver_background

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.receiver_background.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("상태","onCreate()")
        val receiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.d("상태","outer app dynamic receiver")
            }
        }
        registerReceiver(receiver, IntentFilter("ACTION_OUTER_DYNAMIC_RECEIVER"))

        val intent = Intent("ACTION_OUTER_DYNAMIC_RECEIVER")
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            startForegroundService(intent)
            //startForegroundService()함수를 이용하여 서비스를 실행 할 수 있지만, 빨리 알림을 이용해 앱을 포그라운드 상황으로 만들어야한다
        } else {
            startService(intent)
        }
        //매니페스트에 등록한 리시버를 암시적으로 실행할 때는 같은 앱의 리시버든 외부 앱의 리시버든 실행되지 않는다(리시버의 백드라운드 제약)
    }
}