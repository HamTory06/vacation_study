package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import com.example.broadcastreceiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("상태","onCreate()")
//        val intent = Intent(this, MyReceiver::class.java)
//        sendBroadcast(intent)
        val receiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                when(intent?.action){
                    Intent.ACTION_SCREEN_ON -> Log.d("상태","screen on")
                    Intent.ACTION_SCREEN_OFF -> Log.d("상태","screen off")
                }
                when(intent?.action){
                    Intent.ACTION_BATTERY_OKAY -> Log.d("상태","ACTION_BATTERY_OKAY")
                    Intent.ACTION_BATTERY_LOW -> Log.d("상태","ACTION_BATTERY_LOW")
                    Intent.ACTION_BATTERY_CHANGED -> Log.d("상태","ACTION_BATTERY_CHANGED")
                    Intent.ACTION_POWER_CONNECTED -> Log.d("상태","ACTION_POWER_CONNECTED")
                    Intent.ACTION_POWER_DISCONNECTED -> Log.d("상태","ACTION_POWER_DISCONNECTED")
                }
            }
        }
        binding.battery.setOnClickListener {
            val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            val batteryStatus = registerReceiver(null,intentFilter)
            val status = batteryStatus!!.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
            val level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL,-1)
            val scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE,-1)
            val batteryPct = level/ scale.toFloat() * 100
            Log.d("상태","batteryPct : $batteryPct")
            if(status == BatteryManager.BATTERY_STATUS_CHARGING){
                //전원이 공급되고 있다면
                val chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
                when(chargePlug){
                    BatteryManager.BATTERY_PLUGGED_USB -> Log.d("상태","usb charge") //저속충전
                    BatteryManager.BATTERY_PLUGGED_AC -> Log.d("상태","ac charge") //고속충전
                }
            }
            else{
                Log.d("상태","not charging")
            }
        }
        val filter = IntentFilter(Intent.ACTION_SCREEN_ON).apply {
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(Intent.ACTION_BATTERY_OKAY)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_BATTERY_CHANGED)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(receiver, filter)
//        unregisterReceiver(receiver)
    }
}