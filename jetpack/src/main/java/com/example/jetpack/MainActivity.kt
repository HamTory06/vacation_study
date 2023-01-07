package com.example.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.jetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mbinding : ActivityMainBinding ?= null
    private val binding get() = mbinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //메니페스트에서  android:parentActivityName=".MainActivity"를 추가하지 않고 액티비티 코드로 업버튼이 나오게함
    }

    override fun onSupportNavigateUp(): Boolean { //업 버튼을 틀릭 했을 때
        Log.d("상태","뒤로가기 버튼 클릭")
        onBackPressed()//왜지 아무튼 이전 화면으로 되돌아가는 코드
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //메뉴 구성 함수
        for(i in 1..10){
            val meunItem: MenuItem? = menu?.add(0,i,0,"meun${i}") //메뉴 10개 만들기
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean  = when(item.itemId){ //메뉴 선택 시 이벤트 처리
        1 -> {
            Log.d("상태","meun1")
            true
        }
        2 -> {
            Log.d("상태","meun2")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
    override fun onDestroy() {
        super.onDestroy()
        mbinding = null
    }
}