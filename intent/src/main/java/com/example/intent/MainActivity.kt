package com.example.intent

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.intent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val intent: Intent = Intent(this,DetailActivity::class.java)
        val requsetLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            val resultData = it.data?.getStringExtra("result")
        }
        val intent: Intent = Intent(this,DetailActivity::class.java)
        requsetLauncher.launch(intent)
        intent.putExtra("data1","Hello")
        intent.putExtra("data2",10)
        binding.button.setOnClickListener {
//            Log.d("상태","button")
//            startActivity(intent) //사후 처리가 필요 없으면
//            startActivityForResult(intent,10) //사후 처리가 필요하다면
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 10 && resultCode == Activity.RESULT_OK){
            val result = data?.getStringExtra("resultData")
                Log.d("상태","result : $result")
        }
    }
}
