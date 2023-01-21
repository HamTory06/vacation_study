package com.example.ch13_activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.ch13_activity.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean  = when(item.itemId){
        R.id.menu_add_save -> { //저장 버튼을 누르면
            val intent = intent
            intent.putExtra("result",binding.addEditView.text.toString()) //인텐트로 EditText에서 적은 글자를 가져옴
            setResult(Activity.RESULT_OK,intent)
            finish() //엑티비티 종료
            true
        }
        else -> true
    }
}