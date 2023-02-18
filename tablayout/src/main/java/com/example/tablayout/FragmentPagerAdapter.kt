package com.example.tablayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {

    var data: List<Fragment>
    init{
        data = listOf(OneFragment(), TwoFragment(), ThreeFragment())
    }
    override fun getItemCount(): Int = data.size

    override fun createFragment(position: Int): Fragment {
        return data[position]
    }
}