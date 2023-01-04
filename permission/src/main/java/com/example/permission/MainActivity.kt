package com.example.permission

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.permission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cameraButton.setOnClickListener{
            checkPermission()
        }
    }
    fun checkPermission(){
        val camerPermission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)//CAMERA권한 상태 가져오기
        Log.d("카메라 권한",camerPermission.toString())
        if(camerPermission == PackageManager.PERMISSION_GRANTED){
            //카메라 권한이 승인된 경우
            startProcess()
        }
        else{
            //카메라 권한이 승인되지 않았을 경우
            requestPermission()
        }
    }

    fun requestPermission(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission .CAMERA),99)
    }
    fun startProcess(){
        Toast.makeText(this,"카메라 기능 실행", Toast.LENGTH_SHORT).show()
        val intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 1)
    }
}