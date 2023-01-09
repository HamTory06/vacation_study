package com.example.jetpack_viewpager

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack_viewpager.databinding.ActivityMainBinding
import com.example.jetpack_viewpager.databinding.ItemPagerBinding

class MyPagerViewHolder(val binding: ItemPagerBinding): RecyclerView.ViewHolder(binding.root)

class MyPagerAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyPagerViewHolder(ItemPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyPagerViewHolder).binding
        //뷰에 데이터 출력
        binding.itemPagerTextView.text = datas[position%3]
        when(position % 3){
            0 -> binding.itemPagerTextView.setBackgroundColor(Color.RED)
            1 -> binding.itemPagerTextView.setBackgroundColor(Color.BLUE)
            2 -> binding.itemPagerTextView.setBackgroundColor(Color.GREEN)
        }
    }
}

class MainActivity : AppCompatActivity() {
    private var mbinding : ActivityMainBinding ?= null
    private val binding get() = mbinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val datas = mutableListOf<String>()
        for(i in 1..3){
            datas.add("Item $i")
        }
        binding.viewpager.adapter = MyPagerAdapter(datas)
    }
}



