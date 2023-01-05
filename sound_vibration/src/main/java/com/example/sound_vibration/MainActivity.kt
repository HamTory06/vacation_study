package com.example.sound_vibration

import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sound_vibration.databinding.ActivityMainBinding
import java.net.URI

class MainActivity : AppCompatActivity() {
    private var mbinding : ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val ringone = RingtoneManager.getRingtone(applicationContext,notification)
        val player: MediaPlayer = MediaPlayer.create(this,R.raw.sound_effect)

        binding.soundButton.setOnClickListener {
            //ringone.play()
            player.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mbinding = null
    }
}