package com.example.navigationcomponentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.navigationcomponentapp.databinding.FragmentABinding


class FragmentA : Fragment() {

    private var _binding : FragmentABinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentABinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragmentA_to_mainFragment)
        }

        super.onViewCreated(view, savedInstanceState)

    }

}