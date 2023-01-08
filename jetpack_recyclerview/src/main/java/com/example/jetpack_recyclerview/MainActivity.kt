package com.example.jetpack_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack_recyclerview.databinding.ActivityMainBinding
import com.example.jetpack_recyclerview.databinding.ItemMainBinding

class MainActivity : AppCompatActivity(){
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val datas = mutableListOf<String>()
        for(i in 1..10){
            datas.add("Item $i")
        }
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = MyAdapter(datas)
        binding.recyclerview.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))
    }
}

class MyAdapter(val binding: ItemMainBinding): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = datas.size

}