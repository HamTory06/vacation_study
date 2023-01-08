package com.example.jetpack

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.jetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mbinding : ActivityMainBinding ?= null
    private val binding get() = mbinding!!
//    private val callback = object : OnBackPressedCallback(false){ //OnBackPressedCallback(true)로 한이유는 OnBackPressedCallback가 기본활성화 상태를 의미한다 false를 넣으면 handleOnBackPressed()가 호출되지 않고 onBackPressed()처럼 작동하여 액티비티가 finish된다
//        override fun handleOnBackPressed() {
//            Log.d("상태","뒤로가기 틀릭")
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.Toolbar)
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = BlankFragment()
        transaction.add(R.id.fragment_content, fragment)
        transaction.commit()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){ //이렇게 API레벨 호환성을 고려한 반면
//            binding.TextView.lineHeight = 50
//        }
        //binding.AppCompatTextView.lineHeight = 50 //API 레벨 호환성을 고려하지 않아도 된다
        //supportActionBar?.setDisplayHomeAsUpEnabled(true) //메니페스트에서  android:parentActivityName=".MainActivity"를 추가하지 않고 액티비티 코드로 업버튼이 나오게함 이거 쓰면 onSupportNavigateUp함수에서 onBackPressed를 써야되는데 이건 api33에서 지원하지 않는다
        //this.onBackPressedDispatcher.addCallback(this, callback)
        //호환성을 고려한 기본 뷰 클래스
        //AppcomPat(TextView,ImageView,EditText,Button)등을 이용하면 호환성문제를 해결해준다
    }

    override fun onSupportNavigateUp(): Boolean { //업 버튼을 틀릭 시 자동으로 호출되는 함수 재정의(이 함수를 사용하면 onBackPressed 어노케이션된 함수를 사용하게 되어 사용할수 없다)
        Log.d("상태","뒤로가기 버튼 클릭")
        onBackPressed()//어노케이션이 뜬다 뜨는 이유가 예전에는 뒤로가기 버튼으로 뒤로갔지만 요즘은 제스쳐를 이용하여 뒤로가기를 사용하기 때문인데 나는 제스쳐를 이용하지 않기 때문에 onBackPrssed()를 사용해도 상관 없다
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //메뉴 구성 함수
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        val menuItemsearchView = menu?.findItem(R.id.menu_search)
        val searchView = menuItemsearchView?.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("상태",newText!!) // 검색어 변경할떄 마다 실행됨
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // 키보드의 검색 버튼을 클릭한 순간의 이벤트(검색어 출력)
                Log.d("상태",query!!)
                return true
            }
        })
        for(i in 1..10){
            val menuItem: MenuItem? = menu?.add(0,i,0,"meun${i}") //메뉴 10개 만들기
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean  = when(item.itemId){ //메뉴 선택 시 이벤트 처리
        1 -> {
            Log.d("상태","meun1")
            true
        }
        2 -> {
            Log.d("상태","meun2")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        mbinding = null
    }
}