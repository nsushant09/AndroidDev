package com.example.bottomnavigationpractice.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bottomnavigationpractice.R
import com.example.bottomnavigationpractice.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private var _binding : FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingsBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

}