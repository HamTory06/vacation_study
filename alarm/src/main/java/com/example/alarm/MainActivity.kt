package com.example.alarm

import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.alarm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "one-channel"
            val channelName = "My Channel One"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH //알림의 중요도
            )
            channel.description = "My Channel One Description"
            channel.setShowBadge(true) //홈화면에 배지 아이콘 표시
            val uri: Uri =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)//효과음 식별자(URI)취득
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION) //음성 또는 음악과 같은 오디오 신호의 콘텐츠 유형을 설명하는 속성을 설정
                .setUsage(AudioAttributes.USAGE_ALARM) //알람이나 벨소리와 같은 오디오 신호의 용도를 설명하는 속성을 설정
                .build()//설정된 모든 속성을 결합하고 새 AudioAttributes객체를 반환
            channel.setSound(uri, audioAttributes) //알람음 재생
            channel.enableLights(false)//알람 불빛표시 여부
            channel.lightColor = Color.RED
            channel.enableVibration(true)//진동을 울릴지 여부
            channel.vibrationPattern = longArrayOf(100, 200, 100, 200)//진동을 울린다면 진동의 패턴
            //채널을 NotificationManager 등록
            manager.createNotificationChannel(channel)
            //채널을 이용해 빌더 생성
            builder = NotificationCompat.Builder(this, channelId)
        } else {
            builder = NotificationCompat.Builder(this)
        }
        builder.setSmallIcon(android.R.drawable.ic_notification_overlay) //알림 아이콘
        builder.setWhen(System.currentTimeMillis())//알람시간 miliSecond 단위로 넣어주면(currentTimeMillis()) 내부적으로 자동으로 파싱
        builder.setContentTitle("Content Title")
        builder.setContentTitle("Content Massage")
        builder.setAutoCancel(false)//false 알람을 터치해도 알림은 사라지지 않는다
        builder.setOngoing(true) //사용자가 알람을 스와이프해도 사라지지 않는다 알림이 사라지지 않게 하는 코드를 작성하면 결국 cancel()함수로 취소해야된다
        manager.notify(
            11,
            builder.build()
        ) //builder.build()함수가 Notification객체를 만들고 알람이 발생한다 첫 번째 매개변숫값은 알림을 식별하는 데 사용하는 숫자 개발자가 임의로 저장 이 식별값은 사용자 폰에 발생한 알림을 코드에서 취소할 때 사용
        manager.cancel(11)
        //알람 터치 이벤트는 onTouchEvent()함수로 처리 할수 없다 이ㅓㄹ수가, 그래서 이벤트가 발생하면 Notification 객체에 등록된 이벤트 처리 내용을 시스템이 실행하는 구조로 처리
        //시스템에 인텐트를 실행 해달라고 시스템에 의뢰해야 한다 이러한 의뢰는 PendingIntent클래스를 이용해야한다
        //PendingIntent클래스는 컴포넌트별로 실행을 의뢰하는 함수를 제공한다
        val intent = Intent(this,Activity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 10 , intent,
                PendingIntent.FLAG_IMMUTABLE)
        builder.setContentIntent(pendingIntent)
    }

}