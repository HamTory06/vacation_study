package com.example.progress

import android.app.AlertDialog.Builder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.progress.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mbinding : ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val builder: NotificationCompat.Builder
        binding.progressButton.setOnClickListener {
            //builder.setProgress(100,0,false)
        }
    }
}