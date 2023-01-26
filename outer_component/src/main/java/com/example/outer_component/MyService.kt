package com.example.outer_component

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {
    private val binder = object : MyAidllnterface.Stub(){
        override fun test(echo: Int): Int{
            return echo
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}