package com.study.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.study.viewmodel.databinding.ActivityViewModelBinding

class Main : AppCompatActivity() {
    private val binding: ActivityViewModelBinding by lazy { ActivityViewModelBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.d("상태","onCreate()")
    }

    override fun onStart() {
        super.onStart()
        Log.d("상태","onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("상태","onResume()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("상태","onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("상태","onDestroy()")
    }
}