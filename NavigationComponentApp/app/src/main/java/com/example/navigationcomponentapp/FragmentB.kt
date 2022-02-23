package com.example.navigationcomponentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.navigationcomponentapp.databinding.FragmentABinding
import com.example.navigationcomponentapp.databinding.FragmentBBinding


class FragmentB : Fragment() {

    private var _binding : FragmentBBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragmentB_to_mainFragment)
        }


        binding.btnApply.setOnClickListener{
            val messageSend = binding.editName.text.toString()
            if(messageSend.isNotEmpty()){
                val action = FragmentBDirections.actionFragmentBToFragmentC(message = messageSend)
                Navigation.findNavController(view).navigate(action)
            }else{
                Toast.makeText(context,"Empty Field",Toast.LENGTH_SHORT).show()
            }
        }


    }

}