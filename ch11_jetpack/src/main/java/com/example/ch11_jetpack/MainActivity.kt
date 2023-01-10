package com.example.ch11_jetpack

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ch11_jetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    class MyFragmentPagerAdapter(activity: FragmentActivity) :
        FragmentStateAdapter(activity){
            val fragments: List<Fragment> =
                listOf(OneFragment(),TwoFragment(),ThreeFragment(),FourFragment()) //프래그먼트 리스트에 추가

        override fun getItemCount(): Int = fragments.size //항목크기 구하기 위하여 자동으로 호출

        override fun createFragment(position: Int): Fragment = fragments[position] //프레그먼트 만들기
    }

    private var mbinding: ActivityMainBinding?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar) //ActionBer 추가
        //ActionBarDrawerToggle 버튼 적용
        toggle = ActionBarDrawerToggle(this,binding.drawer, R.string.drawer_opande,R.string.dtawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)//토글버튼 추가 true면 토글버튼이 나타나고 false면 안나온다
        toggle.syncState()
        //뷰 페이저에 어댑터 적용
        val adapter = MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //검색해서 찾기
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main,menu)//layout(?)추가
        //MenuItem 객체를 얻고 그 안에 포함된 ActionView 객체 획득
        val meunItem = menu?.findItem(R.id.menu_search)
        val searchView = meunItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{ //키보드의 검색 버튼을 클릭한 순간의 이벤트
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("상태","search text : ${query}")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean { //Text가 변경된 순간의 이벤트
                Log.d("상태","newText : $newText")
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){ //이벤트가 토글 버튼에서 발생하면
            return true //drawerlayout이 나온다
        }
        return super.onOptionsItemSelected(item)
    }
}