package com.example.intent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IInterface
import android.util.Log
import android.widget.Toast
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
        val intent: Intent = Intent(this,DetailActivity::class.java) //명시적 인텐트
//        val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")) //암시적 인텐트(웹 브라우저 열기)
//        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult())
//        {
//            val resultData = it.data?.getStringExtra("result")
//        }
//        val intent = Intent("ACTION_EDIT") //해당 액티비티가 없을 때
        intent.putExtra("data1","Hello")
        intent.putExtra("data2",10)
        binding.button.setOnClickListener {
//            Log.d("상태","button")
            startActivity(intent) //사후 처리가 필요 없으면
//            startActivityForResult(intent,10) //사후 처리가 필요하다면
//            requestLauncher.launch(intent)
//            try { //해당 액티비티가 없을 때 예외 처리
//                startActivity(intent)
//            } catch (e: Exception){
//                Toast.makeText(this,"no app...",Toast.LENGTH_SHORT).show()
//            }
//            requestLauncher.launch(intent)
        }
    }
}
