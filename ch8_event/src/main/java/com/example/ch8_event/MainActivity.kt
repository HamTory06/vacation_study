package com.example.ch8_event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import com.example.ch8_event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mbinding : ActivityMainBinding ?= null //전역변수로 바인딩 객체 선언
    private val binding get() = mbinding!! //매번 null체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언

    var initTime = 0L //Long으로 형 변환 이라 해야되나 그냥 0 Long, 뒤로가기 버튼을 누른 시각을 저장하는 변수(?)
    var pauseTime = 0L //멈춘 시간 저장
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)// 액티비티에서 사용할 바인딩 클래스의 인스턴스 생성
        setContentView(binding.root)

        binding.startButton.setOnClickListener { //시작 버튼을 클릭 했을때
            binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
            binding.chronometer.start()
            binding.resetButton.isEnabled = false //버튼 비활성화
            binding.stopButton.isEnabled = true //버튼 활성화
            binding.startButton.isEnabled = false//버튼 비활성화
        }
        binding.stopButton.setOnClickListener{
            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime() //elapsedRealtime는 시스템이 부팅된 이후 시간을 반환(절전 모드에서 지속)
            //Log.d("elapsedRealtime","${SystemClock.elapsedRealtime()}") //로그로 출력을 하니 너무 큰 수가 나온다 이해를 할수가 없다
            binding.chronometer.stop()
            binding.resetButton.isEnabled = true //버튼 활성화
            binding.stopButton.isEnabled = false //버튼 비활성화
            binding.startButton.isEnabled = true //버튼 활성화
        }
        binding.resetButton.setOnClickListener{ //리셋버튼 누르면
            pauseTime = 0L //리셋버튼 누르면 0으로 초기화
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = false
            binding.startButton.isEnabled = true
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode === KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - initTime > 3000){ //System.currentTimeMillis() 현재 시간 구하는거
                Toast.makeText(this,"종료하려면 한 번 더 누르세요"
                ,Toast.LENGTH_SHORT).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        mbinding = null
        super.onDestroy()
    }
}