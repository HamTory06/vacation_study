package com.example.alarm

import android.app.*
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Icon
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import androidx.core.app.Person
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.alarm.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding? = null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bigPicture = BitmapFactory.decodeResource(resources,R.drawable.send) //사진 저장 Bitmap
        val bigStyle = NotificationCompat.BigPictureStyle()
        val bigTextStyle = NotificationCompat.BigTextStyle()
        bigTextStyle.bigText("침묵은 힘이다.\n" + //글 저장
                "침묵은 진실의 어머니다.\n" +
                "침묵은 영원처럼 깊고 말을 시간처럼 얕다.\n" +
                "침묵은 참된 지혜의 가장 좋은 대답이다.\n" +
                "침묵은 절대 배신하지 않는 진정한 친구다.\n" +
                "침묵이 때로는 가장 잔인한 거짓말을 한다.\n" +
                "침묵은 훌륭한 조정자다.\n" +
                "침묵은 실수를 만들지 않는다.\n" +
                "침묵하는 사람은 어디서든 환영받는다.\n" +
                "침묵은 참기 어려운 재치 있는 응답이다.\n" +
                "사람들은 대부분 말을 너무 많이 한다.\n" +
                "내 성공의 많은 부분은 내가 입을 다물고 있었던 것에서 기인한다.\n" +
                "행복한 삶은 조용한 삶이어야만 한다.\n" +
                "왜냐하면 참다운 기쁨은 오직 조용한 분위기에서만 살아나기 때문이다.\n" +
                "우리 인생의 가장 감동적인 순간에는 아무 말도 하지 못한다. ")
        val style = NotificationCompat.InboxStyle()
        style.addLine("1번쨰 - 물 500cc 끓이기")
        style.addLine("2번째 - 끓는 물에 라면,건더기,스프 넣기")
        style.addLine("3번째 - 3분동안 끓이기")
        style.addLine("4번째 - 맛있게 먹기")

        val sender1: Person = Person.Builder() //Person 객체 생성
            .setName("지나가는 사람1")
            .setIcon(IconCompat.createWithResource(this,R.drawable.person)) //setIcon이 왜 IconCompat형을 받아야되는지는 모르겠지만 (Icon 말고 IconCompat쓰면 됨ㅁ)
            .build()
        val sender2: Person = Person.Builder()
            .setName("지나가는 사람2")
            .setIcon(IconCompat.createWithResource(this,R.drawable.person))
            .build()
        val message1 = NotificationCompat.MessagingStyle.Message(
            "ㅎㅇ",
            System.currentTimeMillis(),
            sender1
        )
        val message2 = NotificationCompat.MessagingStyle.Message(
            "ㅎㅇ?",
            System.currentTimeMillis(),
            sender2
        )
        val messageStyle = NotificationCompat.MessagingStyle(sender1)
            .addMessage(message1) //메시지 추가
            .addMessage(message2) //메시지 추가

        val toast = Toast.makeText(this,"다운 다됨",Toast.LENGTH_SHORT) //토스트 메시지
        val actionIntent =
            Intent(this, Activity::class.java)//class뒤에 .java를 추가하는 이유는 자바로 작성된 API를 코틀린에서 이용하기 때문
        val KEY_TEXT_REPLY = "key_text_reply"
        var replyLabel: String = "답장"
        var remoteInput: RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
            setLabel(replyLabel)
            build()
        }
        val replyTxt = RemoteInput.getResultsFromIntent(intent)?.getCharSequence(
            "key_text_reply"
        )//getCharSequence함수의 매개변수는 RemoteInput를 만들 때 지정한 식별값과 같아야한다
        val replyPendingIntent =
            PendingIntent.getBroadcast(this, 30, actionIntent, PendingIntent.FLAG_MUTABLE)
        val pendingIntent =
            PendingIntent.getActivity(this, 10, actionIntent, PendingIntent.FLAG_IMMUTABLE)
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val requserPermissionLauncher = registerForActivityResult( //알림 권한 있는지 확인
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                Log.d("권한", "권한있음")
            } else {
                Log.d("권한", "권한없음")
            }
        }
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
/*                val uri: Uri =
                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)//효과음 식별자(URI)취득
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION) //음성 또는 음악과 같은 오디오 신호의 콘텐츠 유형을 설명하는 속성을 설정
                    .setUsage(AudioAttributes.USAGE_ALARM) //알람이나 벨소리와 같은 오디오 신호의 용도를 설명하는 속성을 설정
                    .build()//설정된 모든 속성을 결합하고 새 AudioAttributes객체를 반환*/
            //channel.setSound(uri, audioAttributes) //알람음 재생
            channel.enableLights(false)//알람 불빛표시 여부
            channel.lightColor = Color.RED//불빛 색상
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
        builder.setContentText("Content Massage")
        builder.setAutoCancel(true)//알림을 탭하면 자동으로 알림을 삭제
        bigStyle.bigPicture(bigPicture)//잘 모르겠습(사진 띄우는거기 긴한데)
        builder.setStyle(bigStyle)//사진 띄우기
        builder.setStyle(bigTextStyle)//긴 글 띄우기
        builder.setStyle(style)// 상자스타일 하나의 알림에 문자열을 여러 개 나열할 때 유용하다는데 bigTextStyle랑 차이를 모르겠습;;
        builder.setStyle(messageStyle)
        //한번에 setStyle를 여러게 사용 못하는거같음
        //builder.setAutoCancel(false)//false 알람을 터치해도 알림은 사라지지 않는다
        //builder.setOngoing(true) //사용자가 알람을 스와이프해도 사라지지 않는다 알림이 사라지지 않게 하는 코드를 작성하면 결국 cancel()함수로 취소해야된다
//        builder.addAction( //액션 버튼
//            NotificationCompat.Action.Builder(
//                android.R.drawable.stat_notify_more,
//                "Action",
//                pendingIntent
//            ).build()
//        )
//        builder.addAction( //원격 입력
//            NotificationCompat.Action.Builder(
//                R.drawable.send,
//                "답장",
//                replyPendingIntent
//            ).addRemoteInput(remoteInput).build()
//        )
        binding.alarmButton.setOnClickListener {
            //val status = ContextCompat.checkSelfPermission(this,"android.permission.POST_NOTIFICATIONS")
            requserPermissionLauncher.launch("android.permission.POST_NOTIFICATIONS")//권한이 있는지 확인
            //requserPermissionLauncher.launch("android.permission.POST_NOTIFICATIONS")
            //builder.setProgress(100,0,false) //프로그레스 생성
            manager.notify( //알림 갱신
                11,
                builder.build()
            )
            thread{
                for(i in 1..100){
                    builder.setProgress(100,i,false)
                    manager.notify(11,builder.build())
                    SystemClock.sleep(100)
                }
                toast.show()//for문이 끝나면 토스트메시지를 띄우고 알림을 삭제
                manager.cancel(11)//알림 삭제
            }
            //builder.build()//함수가 Notification객체를 만들고 알람이 발생한다 첫 번째 매개변숫값은 알림을 식별하는 데 사용하는 숫자 개발자가 임의로 저장 이 식별값은 사용자 폰에 발생한 알림을 코드에서 취소할 때 사용


            //알람 터치 이벤트는 onTouchEvent()함수로 처리 할수 없다 이ㅓㄹ수가, 그래서 이벤트가 발생하면 Notification 객체에 등록된 이벤트 처리 내용을 시스템이 실행하는 구조로 처리
            //시스템에 인텐트를 실행 해달라고 시스템에 의뢰해야 한다 이러한 의뢰는 PendingIntent클래스를 이용해야한다
            //PendingIntent클래스는 컴포넌트별로 실행을 의뢰하는 함수를 제공한다

            //builder.setContentIntent(pendingIntent) //알람 Action버튼 생성
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mbinding = null
    }
}

