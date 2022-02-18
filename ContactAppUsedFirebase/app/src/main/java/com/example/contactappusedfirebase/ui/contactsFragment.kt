package com.example.contactappusedfirebase.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contactappusedfirebase.R
import com.example.contactappusedfirebase.databinding.FragmentContactsBinding

class contactsFragment : Fragment() {

    //fragmentcontactbinding is the name of the layout i.e fragmetncontact.xml
    private var _binding : FragmentContactsBinding ?= null
    private val binding get() = _binding!!
    private val adapter = ContactAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //rvcontact is the id of recycler view in fragment contacts
        binding.rvContacts.adapter = adapter

        binding.addButton.setOnClickListener{
            AddContactDialogFragment().show(childFragmentManager,"")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}