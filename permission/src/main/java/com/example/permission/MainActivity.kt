package com.example.permission

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
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
        binding.toastButton.setOnClickListener {
            showToast()
        }
        binding.alarmButton.setOnClickListener {
            AlertDialog.Builder(this).run{
                setTitle("test dialog")
                setIcon(android.R.drawable.ic_dialog_info)
                setMessage("알림 창 테스트")
                setPositiveButton("확인",Log.d("알림창_확인",))//알림창에서 확인 버튼 느낌
                setNegativeButton("취소",null)//알림창에서 취소 버튼
                setNeutralButton("MORE",null)//알림창에서 MORE 버튼 자세히 버튼인거같음 잘 모르겠습;;
                show()
            }
        }
        DatePickerDialog(this,object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                Log.d("날짜","${year}년 ${month+1}월 ${dayOfMonth}일") //month값은 0~11까지 지정되있어서 0은 1월을 의미 한다 그래서 +1해주어야됨

            }
        },2023, 0, 4).show()
        TimePickerDialog(this,object: TimePickerDialog.OnTimeSetListener{
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                Log.d("시간","time : ${hourOfDay} minute : ${minute}")
            }
        }, 15,0,false).show() //false면 12시간 true면 24시간
    }

    override fun onDestroy() {
        mbinding = null
        super.onDestroy()
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
    fun showToast(){
        val toast = Toast.makeText(this,"토스트 메시지",Toast.LENGTH_SHORT) //LENGTH_SHORT는 3초동안 LENGTH_LONG는 5초 동안 보여줌
        toast.show()
        toast.addCallback(
            object : Toast.Callback(){
                override fun onToastHidden() {
                    super.onToastHidden()
                    Log.d("Toast","Toast hidden")
                }

                override fun onToastShown() {
                    super.onToastShown()
                    Log.d("Toast","Toast shown")
                }
            }
        )
    }
}