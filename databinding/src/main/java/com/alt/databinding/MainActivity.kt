package com.alt.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.alt.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // xml 파일명이 카멜케이스로 클래스가 자동생성 됩니다.
    private lateinit var binding: ActivityMainBinding

    var text = "Hello!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding 세팅
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // 현재 binding시킨 xml의 variable name
        binding.main = this
        // binding 버튼 클릭 이벤트
        binding.btnChange.setOnClickListener {
            text = "Hello Binding!"
            // Data가 변동될 경우 binding된 View들에 Data 변화를 알려줌
            binding.invalidateAll()
        }
    }
}