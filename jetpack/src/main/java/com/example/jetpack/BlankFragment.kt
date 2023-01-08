package com.example.jetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.jetpack.databinding.ActivityMainBinding
import com.example.jetpack.databinding.FragmentBlankBinding

class BlankFragment : Fragment(){
    lateinit var binding: FragmentBlankBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlankBinding.inflate(inflater,container,false)

        return binding.root
    }
}