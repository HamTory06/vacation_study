package com.study.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel(){
    val count = MutableLiveData<Int>().apply { value = 0 }
    override fun onCleared() {
        super.onCleared()
        Log.d("상태","onCleared()")
    }
}