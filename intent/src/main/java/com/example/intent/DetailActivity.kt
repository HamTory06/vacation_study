package com.example.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.intent.databinding.ActivityDetailBinding
import com.example.intent.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private var mbinding: ActivityDetailBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getIntExtra("data2",0)
        Log.d("상태","data1 : $data1 data2 : $data2")
        binding.button.setOnClickListener {
            intent.putExtra("resultData","world")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}