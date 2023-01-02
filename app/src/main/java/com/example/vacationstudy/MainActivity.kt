package com.example.vacationstudy

import android.content.Context
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = TextView(this).apply{ //람다식임 아님 말고
            typeface = Typeface.DEFAULT_BOLD //글자 폰트 설정
            text = "HamTory"
        }
/*        val name = TextView(this) apply안쓰면 수신객체를 적어야됨
        name.typeface = Typeface.DEFAULT_BOLD
        name.text = "HamTory"*/
        val image = ImageView(this).also{
            it.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.image)) //사진 가져오기
        }
        val address = TextView(this).apply{
            typeface = Typeface.DEFAULT_BOLD //글자 폰트 설정
            text = "대구광역시 달서구 용산동" //textView에 들어갈 글자
        }
        val layout = LinearLayout(this).apply{
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            addView(name,WRAP_CONTENT, WRAP_CONTENT) //name 크기를 가로,세로 WRAP_CONTENT만 폭과 높이가 글자가 꼭 들어갈 정도
            addView(image, WRAP_CONTENT, WRAP_CONTENT)
            addView(address, WRAP_CONTENT, WRAP_CONTENT)
        }
        //LinearLayout 객체를 화면에 출력
        setContentView(layout)
    }
}