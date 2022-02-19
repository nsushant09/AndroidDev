package com.example.bottomnavigationpractice.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bottomnavigationpractice.R
import com.example.bottomnavigationpractice.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding : FragmentMessageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMessageBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

}