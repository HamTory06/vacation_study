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
import android.util.Log
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
        val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
            if(it.all{ permission -> permission.value }){//permission이 허용된 겅우
                noti()//알림함수
            }
            else{
                Toast.makeText(this,"permission denied...", Toast.LENGTH_SHORT).show()//Toast메시지로 3초간 "permission denied" 띄움
            }
        }
        binding.notificationButton.setOnClickListener { //버튼 누르면
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){//api버전 33이상
                if(ContextCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") == PackageManager.PERMISSION_GRANTED//알림permission 허용되었을 경우 참
                ){//android.permission.POST_NOTIFICATIONS이 허용되었을때
                    noti()//알림함수
                }
                else{//아니면
                    permissionLauncher.launch(//이해 불가 (코루틴) 일단 실행하고 잊어버리는 형태의 코루틴(???뭔 소린지;;) 블록내 결과값은 return(반환)하지 않는다
                        arrayOf(
                            "android.permission.POST_NOTIFICATIONS" //아무리 봐도 이해를 못하겠다 배열에다 "android.permission.POST_NOTIFICATIONS"이거를 넣는다는게;; 어디 변수에 이 배열이 들어가는지도 모르겠다 저장이 된건지도 모르겠고
                        )
                    )
                }
            }
            else{//api버전이 33미만일 경우
                noti()//함수 실행
            }
        }
    }

    fun noti(){
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //api 26버전 이상
            val channelId = "one-channel"
            val channelName = "My Channel One"
            val channel = NotificationChannel(//채널 생성
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT//기본 알림 중요도(?) IMPORTANCE_DEFAULT 이거는 모든 곳에 표시되고 소음이 발생하지만 시각적으로 방해되지 않는다
            ).apply {//channel생략해도됨
                description = "My Channel One Description"
                setShowBadge(true) //이 채널에 게시된 알림이 앱아이콘에 표시될 수 있는지 여부
                val uri : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)//소리 Uri로 받아오기
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)//사용자 인터페이스
                    .setUsage(AudioAttributes.USAGE_ALARM)//알람소리
                    .build()
                setSound(uri, audioAttributes)//소리 set
                enableVibration(true)//진동 여부
            }
            //채널을 NotificarionManager에 등록
            manager.createNotificationChannel(channel)
            //채널을 이용하여 builder 생성
            builder = NotificationCompat.Builder(this,channelId)
        }
        else{ //api버전 26미만에는 channe개념이 없다
            builder = NotificationCompat.Builder(this) //만약에 api버전 26이상 일때 Builder(context)만 적으면 Notification을 띄어도 띄어지지 않는다
        }

        //알림 기본 정보
        builder.run {
            setSmallIcon(R.drawable.small)//알림 아이콘 카톡Icon
            setWhen(System.currentTimeMillis())//알림이 언제 왔는지 알려줌(ex 1분전
            setContentTitle("익명의 ")//알림 Title(제목)
            setContentText("ㅎㅇ")//알림 text(내용)
            setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.big))//알림 사진(상대방 프로필 사진, 채팅방 사진)
        }

        val KEY_TEXT_REPLY = "key_text_reply" //remoteInput보내는 메시지 key값
        var repltLabel = "답장"
        var remoteInput: RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
            setLabel(repltLabel)// 원격 입력에서 editText에서 hint같은 느낌
            build()
        }
        val replyIntent = Intent(this,ReplyReceiver::class.java)
        val replyPendingIntent = PendingIntent.getBroadcast( //PendingIntent는 우리가 준비한 Intent를 실행 시켜야하지만 실행시키는 시점은 어떠한 이벤트가 일어났을 떄 실행시켜야하기 때문에 잠시 준비하는 시간을 주는

            this,30,replyIntent,PendingIntent.FLAG_MUTABLE //4번쨰 매개변수는 똑같은 알림이 발생했을 때 어떻게 처리해야 하는지를 나타냄
        )

        builder.addAction( //Action 추가
            NotificationCompat.Action.Builder(
                R.drawable.send, //원격 입력 전송 아이콘
                "답장",//Action버튼
                replyPendingIntent//Intent등록
            ).addRemoteInput(remoteInput).build()//addRemoteInput(remoteInput) 원격 입력
        )

        manager.notify(11,builder.build())//알림 갱신
    }
}