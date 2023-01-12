package com.example.material_library_actionbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.material_library_actionbutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.extendedFad.setOnClickListener{
            when(binding.extendedFad.isExtended){
                true -> binding.extendedFad.shrink() //축소
                false -> binding.extendedFad.extend() //연장
            }
        }
    }
}