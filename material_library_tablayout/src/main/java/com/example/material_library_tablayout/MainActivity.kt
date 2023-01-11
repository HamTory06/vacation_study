package com.example.material_library_tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.BaseAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.material_library_tablayout.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){

    var fragment: List<Fragment>
    init {
        fragment = listOf(OneFragment(),TwoFragment(),ThreeFragment())
        Log.d("상태","fragment size : ${fragment.size}")
    }

    override fun getItemCount(): Int = fragment.size

    override fun createFragment(position: Int): Fragment {
        Log.d("상태","fragment : $position")
        return fragment[position]
    }

}

class MainActivity : AppCompatActivity() {
    private var mbinding : ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayout = binding.tabs

        val tab1: TabLayout.Tab = tabLayout.newTab()
        tab1.text = "Tab1"
        tabLayout.addTab(tab1)

        val tab2: TabLayout.Tab = tabLayout.newTab()
        tab2.text = "Tab2"
        tabLayout.addTab(tab2)

        val tab3: TabLayout.Tab = tabLayout.newTab()
        tab3.text = "Tab3"
        tabLayout.addTab(tab3)

        //뷰에 어댑터 적용
        val adapter = MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = adapter
        TabLayoutMediator(binding.tabs, binding.viewpager){ tab,position ->
            tab.text = "Tab${position + 1}"
        }.attach()

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            //탭 버튼을 선택할 때 이벤트
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transient = supportFragmentManager.beginTransaction()

                when(tab?.text){
                    "Tab1" -> {
                        transient.replace(R.id.viewpager, OneFragment())
                        Log.d("상태","${tab?.text}")
                    }
                    "Tab2" -> {
                        transient.replace(R.id.viewpager, TwoFragment())
                        Log.d("상태","${tab?.text}")
                    }
                    "Tab3" -> {
                        transient.replace(R.id.viewpager, ThreeFragment())
                        Log.d("상태","${tab?.text}")
                    }
                }
                transient.commit()
            }
            // 선택된 탭 버튼을 다시 선택할 때 이벤트
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val transient = supportFragmentManager.beginTransaction()

                when(tab?.text){
                    "Tab1" -> transient.replace(R.id.viewpager, OneFragment())
                    "Tab2" -> transient.replace(R.id.viewpager, TwoFragment())
                    "Tab3" -> transient.replace(R.id.viewpager, ThreeFragment())
                }
                transient.commit()
            }
            // 다른 탭 버튼을 눌러 선택된 탭 버튼이 해체될 때 이벤트
            override fun onTabReselected(tab: TabLayout.Tab?) {
                val transient = supportFragmentManager.beginTransaction()
                when(tab?.text){
                    "Tab1" -> transient.replace(R.id.viewpager, OneFragment())
                    "Tab2" -> transient.replace(R.id.viewpager, TwoFragment())
                    "Tab3" -> transient.replace(R.id.viewpager, ThreeFragment())
                }
                transient.commit()
            }

        })


    }
}
