package com.example.datadase

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.datadase.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mbinding : ActivityMainBinding ?= null
    private val binding get() = mbinding!!

    lateinit var filePath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val file: File = File(getExternalFilesDir(null), "text.txt")
//        val writeStream: OutputStreamWriter = file.writer()
//        writeStream.write("hello world")
//        writeStream.flush()
//        Log.d("상태","${file?.absolutePath}") //파일 위치
//        //파일 읽기
//        val readStream: BufferedReader = file.reader().buffered()
//        readStream.forEachLine {
//            Log.d("상태","$it")
//        }

        var requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
//            val bitmap = it?.data?.extras?.get("data") as Bitmap
            val option = BitmapFactory.Options()
            option.inSampleSize = 10 //OOM오류를 방지하기 위하여
            val bitmap = BitmapFactory.decodeFile(filePath, option)
        }



        if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED){ //외장 메모리 사용 가능 여부 판단
            Log.d("상태","ExternalStorageState MOUNTED")
        } else {
            Log.d("상태","ExternalStorageState UNMOUNTED")
        }
//        val file: File = File(getExternalFilesDir(null), "test.txt")
//        val writeStream: OutputStreamWriter = file.writer()
//        writeStream.write("hello world")
//        writeStream.flush()
//        val readStream: BufferedReader = file.reader().buffered()
//        readStream.forEachLine {
//            Log.d("상태","$it")
//        }
//        Log.d("상태","${file?.absolutePath}") //파일 위치


        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File ?= getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
        filePath = file.absolutePath
        //파일 Uri 획득
        val photoURI: Uri = FileProvider.getUriForFile(
            this,
            "com.example.datadase.fileprovider", file
        )

        //카메라 실행
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        requestCameraFileLauncher.launch(intent)

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME
        )

        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )

        cursor?.let {
            while (cursor.moveToNext()){
                Log.d("상태","_id : ${cursor.getLong(0)}, name : ${cursor.getString(1)}")
            }
        }

            val contentUri: Uri = ContentUris.withAppendedId(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                cursor!!.getLong(0)
            )
            val resolver = applicationContext.contentResolver
            resolver.openInputStream(contentUri).use { stream ->
                // stream 객체에서 작업 수행
                val option = BitmapFactory.Options()
                option.inSampleSize = 10
                val bitmap = BitmapFactory.decodeStream(stream, null, option)
                binding.imageview.setImageBitmap(bitmap)
            }


//        val db_1 = openOrCreateDatabase("testdb_1", Context.MODE_PRIVATE, null) //데이터베이스 객체 생성
////        val db_2 = openOrCreateDatabase("testdb_2", Context.MODE_PRIVATE, null)
////        val db: SQLiteDatabase = DBHelper(this).writableDatabase //베이터 베이스 객체 생성()
//        //테이블 생성(create 문)
//        db_1.execSQL("create table USER_TB (" +
//                "_id integer primary key autoincrement," +
//                "name not null," +
//                "phone)")
//
//        //데이터 삽입(insert 문)
//        db_1.execSQL("insert into USER_TB(name, phone) values (?,?)",
//            arrayOf<String>("HamTory", "010-4607-0741"))
//
//        //데이터 조회(select 문)
//        val cursor_1 = db_1.rawQuery("select * from USER_TB", null)
//
//        //선택한 행의 값 가져오기
//        while(cursor_1.moveToNext()){
//            val name = cursor_1.getString(0)
//            val phone = cursor_1.getString(1)
//        }
//        //insert()함수 사용
////        val values = ContentValues()
////        values.put("name","HamTory")
////        values.put("phone","010-4607-0741")
////        db_2.insert("USER_TB", null, values)
//
//        //query() 함수 사용
////        val cursor_2 = db_2.query("USER_TB", arrayOf<String>("name","phone"), "phone=?", arrayOf<String>("010-4607-0741"), null, null, null)
//
//
//
//        val file = File(filesDir, "test1.txt") //파일 객체 생성
//        val writeStream: OutputStreamWriter = file.writer()
//        writeStream.write("hello world") //데이터 쓰기
//        writeStream.flush() //끝
//
//        val readStream: BufferedReader = file.reader().buffered()
//        readStream.forEachLine {
//            Log.d("상태","$it") //파일 데이터 읽기
//        }
//
//
//        openFileOutput("test2.txt", Context.MODE_PRIVATE).use {
//            it.write("hello world".toByteArray())
//        }
//        openFileInput("test2.txt").bufferedReader().forEachLine {
//            Log.d("상태","$it")
//        }
    }
}