package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) { //리시버 생명주기 함수는 onReceive() 하나뿐
    //onReceive()함수는 실행한 후 10초 이내에 완료할 것을 권장
        Log.d("상태","onReceive()")
    }
}