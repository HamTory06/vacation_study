package com.example.sound_vibration

import android.content.Context
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.*
import android.os.Build.VERSION.SDK_INT
import androidx.appcompat.app.AppCompatActivity
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
        val vibrator = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){ //api버전 31이전 버전에서는 VIBRATOR_SERVICE로 식별되는 시스템 서비스를 이용했지만 31버전 부터는 VIBRATOR_MANAGER_SERVICE로 식별되는 VibratorManager라는 시스템 서비스를 얻고 이 서비스에서 Vibrator를 이용해야된다
            val vibratorManager = this.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        }
        else{ //버전이 31이전일 경우 VIBRATOR_SERVICE를 사용
            getSystemService(VIBRATOR_SERVICE) as Vibrator
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){//api버전이 26이상일 경우
            vibrator.vibrate(
                VibrationEffect.createOneShot(500,
                VibrationEffect.DEFAULT_AMPLITUDE))
        }
        else{ //api버전이 26미만일 경우
            vibrator.vibrate(500)
        }

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