package com.example.service_component

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import com.example.service_component.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var aidlService: MyAidlInterface
    private var mbinding : ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("상태","onCreate()")
//        startService() 함수로 실행
//        백그라운드 작업은 필요하지만 액티비티와 데이터를 주고 받을 일이 없는 등 서로 관련이 없다면 startService() 함수로 서비스를 실행
//        onCreate() -> onStartCommand() -> 서비스실행 -> onDestroy()
//        val intent = Intent(this, MyService::class.java)
//        startService(intent) //서비스 실행
//
//        val intent = Intent("ACTION_OUTER_SERVICE") //암시적 인텐트로 실행
//        intent.setPackage("com.example.service_component")
//        startService(intent)
//
//        stopService(intent) //서비스 종료
//        bindService() 함수로 실행
//        서비스와 액티비티가 상호작용 해야 할떄가 있으면 bindService()
//        onCreate() -> onBind() -> 서비스실행 -> onUnbind() -> onDestroy()
        val intent = Intent("ACTION_AIDL_SERVICE")
        intent.setPackage("com.example.outer_component")
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
//        val intent = Intent("ACTION_OUTER_SERVICE")
//        intent.setPackage("com.example.permission") //프로세스 간 통신에서는 주고받는 데이터는 Parcelable이나 Bundle 타입이어야 한다
//        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }
    val connection: ServiceConnection = object : ServiceConnection { //bindService()함수로 실행

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            //bindService() 함수로 서비스를 구동할 때 자동으로 호출
            aidlService = MyAidlInterface.Stub.asInterface(service)
            Log.d("상태","${aidlService.funA("hello")}")
//            messenger = Messenger(service)
//            val bundle = Bundle() //데이터를 Bundle에 담고 다시 Message 객체에 담아서 전달한다
//            bundle.putString("data1", "hello")
//            bundle.putInt("data2",10)
//            val msg = Message()
//            msg.what = 10
//            msg.obj = "hello"
//            msg.obj = bundle
//            messenger.send(msg)
//            val servideBinder = service as MyService.MyBinder
//            Log.d("상태", servideBinder.funB(10).toString())
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            //unbindService() 함수로 서비스를 종료할 때 자동으로 호출
            Log.d("상태","onServiceDisconnected...")
        }
    }
}