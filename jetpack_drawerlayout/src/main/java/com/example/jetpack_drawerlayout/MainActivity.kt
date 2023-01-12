package com.example.jetpack_drawerlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.jetpack_drawerlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //ActionBarDrawerToggle 버튼 적용
//        toggle = ActionBarDrawerToggle(this,binding.drawer, R.string.drawer_opened,R.string.drawer_closed)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true) //토글버튼으로 사용할 아이콘 출력
//        toggle.syncState()
        binding.mainDrawerView.setNavigationItemSelectedListener {
            Log.d("상태","navigation item click ${it.title}")
            true
        }
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(toggle.onOptionsItemSelected(item)){
//            return true
//       }
//        return super.onOptionsItemSelected(item)
//    }
}