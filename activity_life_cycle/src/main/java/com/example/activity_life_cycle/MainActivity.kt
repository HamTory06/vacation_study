package com.example.activity_life_cycle

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.example.activity_life_cycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("상태","onCreate()")
        val manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        binding.showInputButton.setOnClickListener {
            binding.edittext.requestFocus() //뷰에 포커스 강제
            manager.showSoftInput(binding.edittext, InputMethodManager.SHOW_IMPLICIT) //showSoftInput() 함수를 이용할 때 첫번째 매개변수가 글이 입력될 뷰인데 이뷰가 포커스를 가지지 않은 상태라면 키보드가 나타나지 않는다
//            manager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0)
        }
        binding.hideInputButton.setOnClickListener {
            manager.hideSoftInputFromWindow(currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS)
        }

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){ //API래벨 30이상
//            window.setDecorFitsSystemWindows(false)
//            val controlller = window.insetsController
//            if(controlller != null){
//                controlller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
//                controlller.systemBarsBehavior =
//                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//            } else {
//                window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN)
//            }
//        }
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

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) { //이 함수가 호출된다는 것은 액티비티가 종료된다는 의미이다, Bundle에 데이터를 담아주면 자동으로 데이터를 파일로 저장해준다
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString("data1","hello")
        outState.putInt("data2",0)
        Log.d("상태","onSaveInstanceState()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val data1 = savedInstanceState.getString("data1")
        val data2 = savedInstanceState.getInt("data2")
    }

    override fun onStop() { //액티비티가 더 이상 표시되지 않음
        super.onStop()
        Log.d("상태","onStop()")
    }

    override fun onDestroy() { //시스템이 액티비티를 종료하거나 소멸
        super.onDestroy()
        Log.d("상태","onDestroy()")
    }

}