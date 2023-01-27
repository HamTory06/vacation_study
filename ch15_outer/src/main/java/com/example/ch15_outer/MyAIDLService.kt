package com.example.ch15_outer

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MyAIDLService : Service() {
    lateinit var player: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        //MediaPlayer생성
        player = MediaPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        //MediaPlayer 개체와 연결된 리소스를 해제
        player.release()
    }

    override fun onBind(intent: Intent): IBinder {
        return object : MyAidlInterface.Stub(){
            override fun getMaxDuration(): Int {
                return if(player.isPlaying)
                    player.duration
                else 0
            }

            override fun start() {
                if(!player.isPlaying){
                    player = MediaPlayer.create(this@MyAIDLService, R.raw.music)
                    try { //음악 시작
                        player.start()
                    } catch (e: Exception){
                        e.printStackTrace()
                    }
                }
            }

            override fun stop() {
                if(player.isPlaying)
                    player.stop()
            }

        }
    }
}