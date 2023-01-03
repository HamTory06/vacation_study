package com.example.ch6_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import androidx.activity.OnBackPressedCallback
import com.example.ch6_view.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onBackPressedDispatcher.addCallback(this,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                //뒤로가기 클릭 시 실행 시킬 코드
                Log.d("뒤로가기","Back")
            }
        })
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean { //핸드폰에 있는 소프트 키보드는 onKeyDown()등의 함수가 동작하지 않는다 하지만 안드로이드 기기에서 하드웨어 키보드가 있는데(소리 버튼 뒤로가기,홈,오버뷰,전원버튼) 그 버튼들은 된다
        Log.d("상태",keyCode.toString()) // 0키를 입력해도 0이 출력 되지 않는다 이유는 모르겠다 아스키 코드를 이용한것도 아니다 Int형을 강제로 String형으로 변환해서 이상하게 나오는거같다
        when(keyCode){
            KeyEvent.KEYCODE_0 -> Log.d("상태", "0키 입력")
            KeyEvent.KEYCODE_BACK -> Log.d("상태","뒤로가기 버튼 클릭")
            KeyEvent.KEYCODE_VOLUME_UP -> Log.d("상태","소리 키우기 버튼 클릭")
            KeyEvent.KEYCODE_VOLUME_DOWN -> Log.d("상태","소리 줄이기 버튼 클릭")
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() { //onKeyDown에서 위에 있는것처럼 KeyEvent.KEYCODE_BACK -> 이렇게 안하고 함수로 이용할수 있다 *근데 API33부터 deprecated됨 OnBackPressedCallback()함수 이용을 권장
        Log.d("상태","Back")
        super.onBackPressed()
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("상태",keyCode.toString())
        return super.onKeyUp(keyCode, event)
    }
}