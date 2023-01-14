package com.example.activity_life_cycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("상태","onCreate()")
    }

    override fun onStart() { //onRestart()함수를 통해 돌아옴
        super.onStart()
        Log.d("상태","onStart()")
    }

    override fun onResume() { //액티비티 실행
        super.onResume()
        Log.d("상태","onResume()")
    }

    override fun onPause() { //또 다른 액티비티가 전면에 나옴
        super.onPause()
        Log.d("상태","onPause()")
    }

    override fun onStop() { //액티비티가 더 이상 표시되지 않음
        super.onStop()
        Log.d("상태","onStop()")
    }

    override fun onDestroy() { //시스템이 액티비티를 종료하거나 소멸
        super.onDestroy()
        Log.d("상태","onDestroy()")
    }

    override fun onRestart() { //사용자가 액티비티를 탐색함
        super.onRestart()
    }
}