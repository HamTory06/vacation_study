package com.example.jetpack_recyclerview

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.jetpack_recyclerview.databinding.ActivityMainBinding
import com.example.jetpack_recyclerview.databinding.ItemMainBinding

class MyViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas: List<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int = datas.size //항목 개수를 판단 하려고 자동을로 호출된다

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = //항목의 뷰를 가지는 뷰 홀더를 준비하려고 자동으로 호출된다
        MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) { //뷰 홀더의 뷰에 데이터를 출력하려고 자동으로 호출된다
        Log.d("상태","onBindViewHolder : $position")//화면에 나오고 있는 데이터
        val binding = (holder as MyViewHolder).binding
        //뷰에 데이터 출력
        binding.itemData.text = datas[position]
        //뷰에 이벤트 추가
        binding.itemRoot.setOnClickListener{
            Log.d("상태","item root click : ${position+1}")
        }
    }
}

class MainActivity : AppCompatActivity(){
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val datas = mutableListOf<String>()
        for(i in 1..100){
            datas.add("Item $i")
        }
        //val layoutManager = GridLayoutManager(this,5,GridLayoutManager.HORIZONTAL,true) //리사이클러뷰 그리드로 배치 spanCount는 열의 개수(2는 2열),가로,true는 왼쪽정렬 false는 오른쪽정렬
        //val layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)//StaggeredGridLayoutManager는 높이가 불규칙한 그리드로 배치하기
        val layoutManager = LinearLayoutManager(this)//리사이클러뷰 세로 설정
        //layoutManager.orientation = LinearLayoutManager.HORIZONTAL//리사이클러뷰 가로설정
        //binding.recyclerview.layoutManager = layoutManager//리사이클러뷰 가로
        //binding.recyclerview.layoutManager = LinearLayoutManager(this)//리사이클러뷰 세로
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.adapter = MyAdapter(datas)
        binding.recyclerview.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))
        binding.addRecyclerviewButton.setOnClickListener {
            datas.add("new data")
            (binding.recyclerview.adapter as MyAdapter).notifyDataSetChanged()
        }
    }
}

class MyDecoration(val context: Context): RecyclerView.ItemDecoration(){
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) { //항목이 배치되기 전에 호출된다
        super.onDraw(c, parent, state)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) { //항목이 모두 배치된 후 호출된다
        super.onDrawOver(c, parent, state)
    }

    override fun getItemOffsets( //개별 항목을 꾸밀 때 호출된다
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
    }
}

