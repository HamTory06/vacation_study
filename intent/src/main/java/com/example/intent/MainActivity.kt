package com.example.intent

import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_EDIT
import android.content.Intent.ACTION_VIEW
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
//        val intent: Intent = Intent(this,DetailActivity::class.java) //명시적 인텐트
//        val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")) //암시적 인텐트(웹 브라우저 열기)
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.7749,127.4194")) //지도 앱의 액티비티를 실행하는 인텐트(암시적 인텐트)
//        intent.setPackage("com.google.android.apps.maps") //setPackage()함수는 식별자가 가리키는 앱의 액티비티가 실행
        val packageInfo = packageManager.getPackageInfo("com.example.ch12_material",0) //안드로이드 11버전부터는 매니페으스 파일에 외부 앱의 정보에 접근하겠다고 선언해주어야함
        val versionName = packageInfo.versionName
//        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult())
//        {
//            val resultData = it.data?.getStringExtra("result")
//        }
//        val intent = Intent("ACTION_EDIT") //해당 액티비티가 없을 때
//        intent.putExtra("data1","Hello")
//        intent.putExtra("data2",10)
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
