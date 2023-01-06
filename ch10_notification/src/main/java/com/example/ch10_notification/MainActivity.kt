package com.example.ch10_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.core.content.ContextCompat
import com.example.ch10_notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mbinding : ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){
            if(it.all{ permission -> permission.value == true}){
                //noti()
            }
            else{
                Toast.makeText(this,"permission denied...", Toast.LENGTH_SHORT).show()
            }
        }
        binding.notificationButton.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                if(ContextCompat.checkSelfPermission(
                        this,
                        "android.permission.POST_NOTIFICATIONS"
                    ) == PackageManager.PERMISSION_GRANTED
                ){
                    noti()
                }
                else{
                    permissionLauncher.launch(
                        arrayOf(
                            "android.permission.POST_NOTIFICATIONS"
                        )
                    )
                }
            }
            else{
                noti()
            }
        }
    }

    fun noti(){
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //api 26버전 이상
            val channelId = "one-channel"
            val channelName = "My Channel One"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT//기본 알림 중요도(?) IMPORTANCE_DEFAULT이거는 모든 곳에 표시되고 소음이 발생하지만 시각적으로 방해되지 않는다
            ).apply {
                description = "My Channel One Description"
                setShowBadge(true) //이 채널에 게시된 알림이 앱아이콘에 표시될 수 있는지 여부
                val uri : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)//소리 Uri로 받아오기
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)//사용자 인터페이스
                    .setUsage(AudioAttributes.USAGE_ALARM)//알람소리
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }
            //채널을 NotificarionManager에 등록
            manager.createNotificationChannel(channel)
            //채널을 이용하여 builder 생성
            builder = NotificationCompat.Builder(this,channelId)
        }
        else{ //api버전 26미만
            builder = NotificationCompat.Builder(this)
        }

        //알림 기본 정보
        builder.run {
            setSmallIcon(R.drawable.small)
            setWhen(System.currentTimeMillis())
            setContentTitle("익명의 ")
            setContentText("ㅎㅇ")
            setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.big))
        }

        val KEY_TEXT_REPLY = "key_text_reply"
        var repltLabel = "답장"
        var remoteInput: RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
            setLabel(repltLabel)
            build()
        }
        val replyIntent = Intent(this,ReplyReceiver::class.java)
        val replyPendingIntent = PendingIntent.getBroadcast(
            this,30,replyIntent,PendingIntent.FLAG_MUTABLE
        )

        builder.addAction(
            NotificationCompat.Action.Builder(
                R.drawable.send,
                "답장",
                replyPendingIntent
            ).addRemoteInput(remoteInput).build()
        )

        manager.notify(11,builder.build())
    }
}