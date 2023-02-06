package com.example.preferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.os.PersistableBundle
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.preferences.databinding.ActivityMainBinding
import java.util.function.Consumer

class MainActivity : AppCompatActivity(),
PreferenceFragmentCompat.OnPreferenceStartFragmentCallback{

    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("상태","onCreate")
    }
    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat,
        pref: Preference
    ): Boolean {
        val args = pref.extras
        val fragment = supportFragmentManager.fragmentFactory.instantiate(
            classLoader,
            pref.fragment!!
        )
        fragment.arguments = args
        supportFragmentManager.beginTransaction()
            .replace(R.id.setting_content, fragment)
            .addToBackStack(null)
            .commit()
        return true
    }
}
//    {
//    private var mbinding: ActivityMainBinding? = null
//    private val binding get() = mbinding!!
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        mbinding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val sharedPref_1 = getPreferences(Context.MODE_PRIVATE) //액티비티의 데이터 저장
//        val sharedPref_2 = getSharedPreferences("my_prefs", Context.MODE_PRIVATE) //앱 전체의 데이터 저장
//
//        sharedPref_1.edit().run {
//            putString("data1","hello") //(키값, 저장될값)
//            putInt("data2",10) //(키값, 저장될값)
//            commit()
//        }
//
//        val data1 = sharedPref_1.getString("data1","world")
//        val data2 = sharedPref_2.getInt("data2",10)
//
//        Log.d("상태","data1 : $data1 data2 : $data2")
//
//    }
//
//
//
//}