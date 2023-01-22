package com.example.ch13_activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ch13_activity.databinding.ItemRecyclerviewBinding

class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas: MutableList<String>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int { //항목의 개수 구하기
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder //항목 구성에 필요한 뷰 홀더 객체 준비
            = MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) { //뷰에 데이터 출력
        val binding=(holder as MyViewHolder).binding
        val context = binding.itemRoot.context
        binding.itemData.text = datas!![position]
        binding.itemRoot.setOnClickListener {
            val intent = Intent(context,onClick::class.java)
            intent.putExtra("datas",datas!![position])
            intent.run { context.startActivity(this) }
        }
    }
}
