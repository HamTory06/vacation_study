package com.study.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.study.viewmodel.databinding.ActivityViewModelBinding

class Main : AppCompatActivity() {
    private val binding: ActivityViewModelBinding by lazy { ActivityViewModelBinding.inflate(layoutInflater) }
    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MyViewModel::class.java)
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

    override fun onPause() {
        super.onPause()
        Log.d("상태","onPause()")
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