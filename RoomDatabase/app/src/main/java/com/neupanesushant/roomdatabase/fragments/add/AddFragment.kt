package com.neupanesushant.roomdatabase.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.neupanesushant.roomdatabase.R
import com.neupanesushant.roomdatabase.data.User
import com.neupanesushant.roomdatabase.data.UserViewModel
import com.neupanesushant.roomdatabase.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    private var _binding : FragmentAddBinding ?= null
    private val binding get() = _binding!!

    //initialize viewModel
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnAdd.setOnClickListener{
            insertDataToDatabase()
        }

    }

    private fun insertDataToDatabase() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val age = binding.etAge.text

        if(inputCheck(firstName, lastName, age)){
            //Create user Object
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()) )
            //Add data to database
            mUserViewModel.addUser(user)

            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()

            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable) : Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}