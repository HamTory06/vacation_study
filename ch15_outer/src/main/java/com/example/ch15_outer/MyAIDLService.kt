package com.example.ch15_outer

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class MyAIDLService : Service() {
    lateinit var player: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        Log.d("상태","MyAIDLService_onCreate()")
        //MediaPlayer생성
        player = MediaPlayer()
    }

    override fun onDestroy() {
        Log.d("상태","MyAIDLService_onDestroy()")
        //MediaPlayer 개체와 연결된 리소스를 해제
        player.release()
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        return object : MyAidlInterface.Stub(){
            override fun getMaxDuration(): Int {
                return if(player.isPlaying)
                    player.duration
                else 0
            }

            override fun start() {
                Log.d("상태","MyAIDLService_start()")
                if(!player.isPlaying){
                    player = MediaPlayer.create(this@MyAIDLService, R.raw.music)
                    try { //음악 시작
                        player.start()
                    } catch (e: Exception){
                        e.printStackTrace()
                        Log.d("상태","e : ${e.message}")
                    }
                }
            }

            override fun stop() {
                Log.d("상태","MyAIDLService_stop()")
                if(player.isPlaying)
                    player.stop()
            }

        }
    }
}