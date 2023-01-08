package com.example.jetpack

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.jetpack.databinding.ActivityMainBinding
import com.example.jetpack.databinding.FragmentBlankBinding

class BlankFragment : Fragment(){
    lateinit var binding: FragmentBlankBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("프래그먼트 생명주기","onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("프래그먼트 생명주기","onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlankBinding.inflate(inflater,container,false)
        Log.d("프래그먼트 생명주기","onCreateView()")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("프래그먼트 생명주기","onViewCreated()")
    }

    override fun onStart() {
        super.onStart()
        Log.d("프래그먼트 생명주기","onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("프래그먼트 생명주기","onResume()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("프래그먼트 생명주기","onStop()")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("프래그먼트 생명주기","onDestroyView()")

    }

}