package com.example.service_component

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.widget.Toast

class MyService : Service() {
    lateinit var messenger: Messenger
    internal class IncomingHandler(
        context: Context,
        private val applicationContext: Context = context.applicationContext
    ) : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            when(msg.what){
                10 -> {
                    Log.d("상태","${msg.obj}")
                    Toast.makeText(applicationContext, "${msg.obj}",Toast.LENGTH_SHORT).show()
                }
                20 -> {
                    Log.d("상태","${msg.obj}")
                    Toast.makeText(applicationContext, "${msg.obj}",Toast.LENGTH_SHORT).show()
                }
                else -> {
                    super.handleMessage(msg)
                    Log.d("상태","${msg.obj}")
                }
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        messenger = Messenger(IncomingHandler(this))
        Log.d("상태","onBind")
        return messenger.binder
    }
}