package com.example.service_component

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyAIDLService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return object : MyAidlInterface.Stub(){
            override fun funA(data: String?) {
            }

            override fun funB(): Int {
                return 10
            }

        }
    }
}