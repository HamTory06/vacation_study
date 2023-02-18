package com.example.tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tablayout.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding? = null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tablelayout = binding.tabs

        val tab1: TabLayout.Tab = tablelayout.newTab().also {
            it.text = "Tab1"
            tablelayout.addTab(it)
        }
        val tab2: TabLayout.Tab = tablelayout.newTab().also {
            it.text = "Tab2"
            tablelayout.addTab(it)
        }
        val tab3: TabLayout.Tab = tablelayout.newTab().also {
            it.text = "Tab3"
            tablelayout.addTab(it)
        }
        val adapter = FragmentPagerAdapter(this)
        binding.viewpager.adapter = adapter
        TabLayoutMediator(binding.tabs, binding.viewpager){ tab, position ->
            tab.text = "Tab${(position + 1)}"
        }.attach()

//        tablelayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
//            //탭 버튼을 선택할 때 이벤트
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                val transaction = supportFragmentManager.beginTransaction()
//                when(tab?.text){
//                    "Tab1" ->transaction.replace(R.id.viewpager, OneFragment())
//                    "Tab2" -> transaction.replace(R.id.viewpager, TwoFragment())
//                    "Tab3" -> transaction.replace(R.id.viewpager, ThreeFragment())
//                }
//                transaction.commit()
//            }
//            //선택된 탭 버튼을 다시 선택할 때 이벤트
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//
//            }
//            //다른 탭 버튼을 눌러 선택된 탭 버튼이 해제될 때 이벤트
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//
//            }
//
//        })
    }
}