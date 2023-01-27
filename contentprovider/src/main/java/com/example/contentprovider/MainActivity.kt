package com.example.contentprovider

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.contentprovider.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!

    lateinit var requestLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        requestLauncher.launch(intent)

        val status = ContextCompat.checkSelfPermission(this, "android.permission.READ_CONTACTS")
        if(status == PackageManager.PERMISSION_GRANTED){
            //퍼미션 허용이라면
            Log.d("상태","permission granted")
        } else {
            //퍼미션 요청 다이얼로그 표시
            ActivityCompat.requestPermissions(this, arrayOf<String>("android.permission.READ_CONTACTS"),100)
            Log.d("상태","permission denied")
        }

        requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            if (it.resultCode == RESULT_OK){
                Log.d("상태","RESULT_OK")
                val cursor = contentResolver.query(
                    it!!.data!!.data!!,
                    arrayOf<String>(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    ),
                    null,
                    null,
                    null,
                )
                Log.d("상태태","cursor size....${cursor?.count}")
                if(cursor!!.moveToFirst()){
                    val name = cursor?.getString(0)
                    val phone = cursor?.getString(1)
                    binding.resultContact.text = "name : $name, phone : $phone"
                }
            }
        }
        binding.button.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            requestLauncher.launch(intent)
        }
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
}