package com.example.navigationcomponentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.navigationcomponentapp.databinding.FragmentCBinding


class FragmentC : Fragment() {

    private var _binding : FragmentCBinding ?= null
    private val binding get() = _binding!!

    //create a safe args object
    private val args : FragmentCArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCBinding.inflate(layoutInflater)

        //set the args.messgae to variable and display
        val textMessage = args.message
        binding.textRecieveInput.text = textMessage
        return binding.root
    }


}