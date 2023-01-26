package com.example.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.content.getSystemService
import com.example.jobscheduler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var jobScheduler: JobScheduler ?= getSystemService<JobScheduler>()
        val extras = PersistableBundle()
        extras.putString("extra_data","hello HamTory")
        val builder = JobInfo.Builder(1, componentName)
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED) //네트워크 타입 설정
        builder.setRequiresCharging(true) //배터리가 충전 상태인지 확인
        builder.setExtras(extras)
        val jobInfo = builder.build()
        jobScheduler!!.schedule(jobInfo)

        JobInfo.Builder(1, ComponentName(this, MyJobService::class.java)).run { //잡 인포
            setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            jobScheduler?.schedule(build())
        }
    }
}