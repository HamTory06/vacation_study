package com.example.jobscheduler

import android.annotation.TargetApi
import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class MyJobService : JobService() { //잡 서비스

    override fun onCreate() {
        super.onCreate()
        Log.d("상태","MyJobService_onCreate()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("상태","MyJobService_onDestroy()")
    }

    override fun onStartJob(params: JobParameters): Boolean {
        params.extras.getString("extra_data")
        Log.d("상태","MyJobService_onStartJob()")
        Thread(Runnable {
            var sum = 0
            for(i in 1..10){
                sum += i
                SystemClock.sleep(1000)
            }
            Log.d("상태","JobSchedulerService... onStartJob... thread result : $sum")
            jobFinished(params, false)
        }).start()
        return true //false : 백그라운드 작업이 완벽하게 종료되었음을 의미, true : 작업이 아직 끝나지 않았음을 의미
        //작업이 완벽하게 끝났으면 onStopJob() 함수를 건너뛰고 바로 onDestroy()함수를 호출해 서비스 종료
        //작업이 완벽하게 끝나지 않았으면 onDestroy()함수는 호출되지 않는다
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d("상태","MyJobService_onStopJob()")
        return false //false : 작업이 완벽하게 종료되었음을 알림, true : 작업이 아직 끝나지 않았음을 알림
    }
}