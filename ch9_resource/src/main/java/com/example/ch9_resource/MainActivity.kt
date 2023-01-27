package com.example.ch9_resource

import android.annotation.TargetApi
import android.app.Notification
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.ch9_resource.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mbinding : ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button?.setOnClickListener{

        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            val builder: Notification.Builder = Notification.Builder(this,"1")
/*                .setStyle(
                    Notification.CallStyle.forIncomingCall()
                )*/
        }
    }
    @RequiresApi(Build.VERSION_CODES.S) //안드로이드 S 버전에서만 noti함수는 작동한다
    fun noti(){
        val builder: Notification.Builder = Notification.Builder(this, "1")
/*            .setStyle(
                Notification.CallStyle.forIncomingCall(caller, declineIntent, answerIntent)
            )*/
    }
    @TargetApi(Build.VERSION_CODES.S) //이것도 RequiresApi와 같다
    fun noti1(){
        val builder: Notification.Builder = Notification.Builder(this,"1")
/*            .setStyle(
                Notification.CallStyle.forIncomingCall()
            )*/
    }

}