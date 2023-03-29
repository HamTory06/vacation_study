package com.study.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private val users: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().also {
            loadUsers()
        }
    }

    fun getUsers(): LiveData<Int> {
        return users
    }

    private fun loadUsers() {
        // Do an asynchronous operation to fetch users.
    }
}