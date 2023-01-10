package com.example.jetpack_viewpager2_fragmentstateadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import androidx.viewpager2.widget.ViewPager2
import com.example.jetpack_viewpager2_fragmentstateadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mbinding : ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val fragmentList = listOf(OneFragment(),TwoFragment(),ThreeFragment())

        val adapter = MyFragmentPagerAdapter(this)
        //adapter.fragment = fragmentList
        binding.viewpager.adapter = adapter
        //binding.viewpager.orientation = ViewPager2.ORIENTATION_VERTICAL //뷰 페이저2를 세로로 적용
    }
}

class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){

    var fragment: List<Fragment>
    init{
        fragment = listOf(OneFragment(),TwoFragment(),ThreeFragment())
        Log.d("상태","fragment size : ${fragment.size}")
    }

    override fun getItemCount(): Int = fragment.size

    override fun createFragment(position: Int): Fragment{
        Log.d("상태","fragment : $position")
        return fragment[position]
    }

}