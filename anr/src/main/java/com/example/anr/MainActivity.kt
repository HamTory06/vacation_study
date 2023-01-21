package com.example.anr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.example.anr.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        시간이 오래 걸리는 작업 예
//        var sum = 0L
//        var Time = measureTimeMillis {
//            for (i in 1..2_000_000_000) {
//                sum += i
//            }
//        }
//        Log.d("상태", "time : $Time")
//        binding.textview.text = "sum : $sum"

//        스레드-핸들러 구조로 작성한 소스
//        val handler = object : Handler() {
//            override fun handleMessage(msg: Message) {
//                super.handleMessage(msg)
//                binding.textview.text = "sum : ${msg.arg1}"
//            }
//        }
//        thread {
//            var sum = 0L
//            var time = measureTimeMillis {
//                for (i in 1..2_000_000_000) {
//                    sum += i
//                }
//                val message = Message()
//                message.arg1 = sum.toInt()
//                handler.sendMessage(message)
//            }
//            Log.d("상태", "time : $time")
//        }
//        코루틴으로 작성한 소스
//        Dispatchers.Main 액티비티의 메인 스레드에서 동작하는 코루틴을 만든다
//        Dispatchers.Default CPU를 많이 사용하는 작업을 백그라운드에서 실행한다
//        Dispatchers.IO 파일을 읽거나 쓰기 또는 네트위크 작업 등에 최적화
        val channel = Channel<Int>()
        val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
        backgroundScope.launch {
            var sum = 0L
            var time = measureTimeMillis {
                for(i in 1..2_000_000_000){
                    sum+=i
                }
            }
            Log.d("상태","time : $time")
            channel.send(sum.toInt())
        }
        val mainScope = GlobalScope.launch(Dispatchers.Main){
            channel.consumeEach {
                binding.textview.text = "sum : $it"
            }
        }
    }
}