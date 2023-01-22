package com.example.ch13_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.ch13_activity.databinding.ActivityOnClickBinding

class onClick : AppCompatActivity() {
    private var mbinding: ActivityOnClickBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityOnClickBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var name: String ?= intent.getStringExtra("datas")
        binding.text.text = name
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_back,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.menu_add_save -> {
            finish()
            true
        }
        else -> {
            false
        }
    }
}