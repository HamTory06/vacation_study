package com.example.custom_dialog

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.custom_dialog.databinding.ActivityMainBinding
import com.example.custom_dialog.databinding.DialogInputBinding

class MainActivity : AppCompatActivity() {
    private var mbinding : ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dialogBinding = DialogInputBinding.inflate(layoutInflater)
        binding.customDialog.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("Input")
                setView(dialogBinding.root)
                setPositiveButton("닫기",null)
                show()
            }
        }
    }
}