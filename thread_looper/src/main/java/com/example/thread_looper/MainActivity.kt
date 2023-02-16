package com.example.thread_looper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.annotation.WorkerThread
import com.example.thread_looper.databinding.ActivityMainBinding
import java.lang.String.format
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!

    var total = 0

    var started = false


    val handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            val second = format("%02d", total%60) //초
            val minute = format("%02d", total/60) //분
            binding.time.text = "$minute:$second"
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.start.setOnClickListener {
            if(!started){
                started = true
                thread {
                    while (started){
                        Thread.sleep(1000)
                        if(started){
                            total+=1
                            handler?.sendEmptyMessage(0)
                        }
                    }
                }
            }
        }
        binding.end.setOnClickListener {
            if(started){
                started = false
                total = 0
                binding.time.text = "00:00"
            }

        }



        //람다식으로 Runnable 익명객체 구형
//        Thread{
//            var i = 0
//            while (i < 10){
//                i += 1
//                Log.d("상태","$i")
//            }
//        }.start()

        //코틀린에서 제공하는 Thread() 구현
//        thread {
//            var i = 0
//            while (i < 10){
//                Log.d("상태","$i")
//            }
//        }
//        var thread = WorkerThread()
//        thread.start()
    }
    //스레드는 하나의 프로세스 상의 독립적인 실행 흐름
    //백그라운드 스레드는 UI 구성 요소에 접근하면 안 된다.

    //Thread 객체
//    class WorkerThread: Thread(){
//        override fun run() {
//            var i = 0
//            while (i < 10){
//                i += 1
//                Log.d("상태","$i")
//            }
//        }
//    }

    //Runnable 인터페이스
//    class WorkerRunnable: Runnable{
//        override fun run() {
//            var i = 0
//            while (i < 10){
//                i += 1
//                Log.d("상태","$i")
//            }
//        }
//    }
}