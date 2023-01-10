package com.example.ch11_jetpack

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ch11_jetpack.databinding.FragmentOneBinding
import com.example.ch11_jetpack.databinding.ItemRecyclerviewBinding

class OneFragment : Fragment() {

    var datas = mutableListOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOneBinding.inflate(layoutInflater,container,false)
        //리사이클러 뷰를 위한 가상 데이터 준비
        for(i in 1..9){
            datas.add("Item $i") //리사이클러뷰(항목) 9개 추가
        }
        var data = 10
        //리사이클러 뷰에 LayoutManager, Adapter, ItemDecoration 적용
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = MyAdapter(datas)

        binding.addRecyclerview.setOnClickListener { //버튼을 클릭하면
            datas.add("Item $data") //리사이클러뷰(항목)추가
            data++
            adapter.notifyDataSetChanged() //화면에 나오도록 F5느낌
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(MyDecoration(activity as Context))
        return binding.root
    }

}

//항목 뷰를 가지는 역할
class MyViewHolder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

//항목 구성자. 어댑터
class MyAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    //항목 뷰를 가지는 뷰 홀더를 준비하기 위해 자동 호출
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    //각 항목을 구성하기 위해 호출
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding
        //뷰에 데이터 출력
        binding.itemData.text = datas[position]
        Log.d("상태","onBindViewHolder : ${position+1}")
        binding.itemRoot.setOnClickListener{
            Log.d("상태","OnClick : ${position+1}")
        }
    }

    override fun getItemCount(): Int = datas.size //항목 개수를 판단하기 위해 자동 호출

}

class MyDecoration(val context: Context): RecyclerView.ItemDecoration(){ //리사이클러뷰꾸미기

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) { //모든 항목이 배치된 후 호출된다
        super.onDrawOver(c, parent, state)
        //뷰 크기 계산
        val width = parent.width //가로크기
        val height = parent.height //세로크기
        //이미지 크기 계산
        val dr: Drawable? = ResourcesCompat.getDrawable(context.resources,R.drawable.kbo,null) //dr변수에 사진 저장
        val drWidth = dr?.intrinsicWidth
        val drHeight = dr?.intrinsicHeight
        //이미지를 출력할 위치 계산
        val left = width / 2 - drWidth?.div(2) as Int
        val top = height / 2 - drHeight?.div(2) as Int
        //Log.d("상태","left : $left top : $top")

        c.drawBitmap( //사진 띄우기
            BitmapFactory.decodeResource(context.resources,R.drawable.kbo),
            left.toFloat(), //사진띄울위치 좌우
            top.toFloat(), //사진띄울위치 위아래
            null
        )
    }
    //각 항목을 꾸미기 위해 호출
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val index = parent.getChildAdapterPosition(view) + 1 //0부터 시작이여서 1추가

        if(index % 3 == 0){ //3번째 칸마다
            outRect.set(10,10,10,60)//마진 느낌
        }
        else{
            outRect.set(10,10,10,0)//이것도 마진 느낌
        }
        view.setBackgroundColor(Color.parseColor("#28A0FF")) //각 리사이클러뷰(항목)마다 BackgroundColor변경
        ViewCompat.setElevation(view,0f)//뭔지 잘 모르겠다..
    }
}
