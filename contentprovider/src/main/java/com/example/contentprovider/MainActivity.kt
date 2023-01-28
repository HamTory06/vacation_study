package com.example.contentprovider

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.contentprovider.databinding.ActivityMainBinding
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!

    lateinit var requestLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        requestLauncher.launch(intent)
        val option = BitmapFactory.Options()
        option.inSampleSize = 4
        val Bitmap = BitmapFactory.decodeStream(InputStream.nullInputStream(), null, option)
//        binding.button.setOnClickListener {
//            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            intent.type = "image/*"
//            requestLauncher.launch(intent)
//        }
        binding.button.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            requestLauncher.launch(intent)
        }
        requestLauncher =registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            val bitmap = it?.data?.extras?.get("data") as Bitmap
        }
//        requestLauncher = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult())
//        {
//            try {
//                //inSampleSize 비율 계산, 지정
//                val calRatio = calcuateInSampleSize(it!!.data!!.data!!,
//                resources.getDimensionPixelSize(R.dimen.imgSize),
//                resources.getDimensionPixelSize(R.dimen.imgSize))
//                val option = BitmapFactory.Options()
//                option.inSampleSize=calRatio
//
//                //이미지 로딩
//                var inputStream = contentResolver.openInputStream(it!!.data!!.data!!)
//                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
//                inputStream!!.close()
//                inputStream = null
//                bitmap?. let {
//                    binding.ImageView.setImageBitmap(bitmap)
//                } ?: let{
//                    Log.d("상태","bitmap null")
//                }
//            } catch (e: Exception){
//                e.printStackTrace()
//            }
//        }
//        val status = ContextCompat.checkSelfPermission(this, "android.permission.READ_CONTACTS")
//        if(status == PackageManager.PERMISSION_GRANTED){
//            //퍼미션 허용이라면
//            Log.d("상태","permission granted")
//        } else {
//            //퍼미션 요청 다이얼로그 표시
//            ActivityCompat.requestPermissions(this, arrayOf<String>("android.permission.READ_CONTACTS"),100)
//            Log.d("상태","permission denied")
//        }

//        requestLauncher = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult())
//        {
//            if (it.resultCode == RESULT_OK){
//                Log.d("상태","RESULT_OK")
//                val cursor = contentResolver.query(
//                    it!!.data!!.data!!,
//                    arrayOf<String>(
//                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
//                        ContactsContract.CommonDataKinds.Phone.NUMBER
//                    ),
//                    null,
//                    null,
//                    null,
//                )
//                Log.d("상태태","cursor size....${cursor?.count}")
//                if(cursor!!.moveToFirst()){
//                    val name = cursor?.getString(0)
//                    val phone = cursor?.getString(1)
//                    binding.resultContact.text = "name : $name, phone : $phone"
//                }
//            }
//        }
//        binding.button.setOnClickListener {
//            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
//            requestLauncher.launch(intent)
//        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("상태","onRequestPermissionsResult()")
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.d("상태","permission granted")
        } else {
            Log.d("상태","permission denied")
        }
    }

    private fun calcuateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int{
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try {
            var inputStream = contentResolver.openInputStream(fileUri)
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        //inSampleSize 비울 계산
        if(height > reqHeight || width > reqWidth){
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth){
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}