package com.example.contactappusedfirebase.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.contactappusedfirebase.R
import com.example.contactappusedfirebase.data.Contact
import com.example.contactappusedfirebase.databinding.FragmentAddContactDialogBinding
import com.example.contactappusedfirebase.databinding.FragmentUpdateContactDialogBinding


class UpdateContactDialogFragment(val contact : Contact) : DialogFragment() {

    //binding the fragment_add_contact_dialog to this class
    private var _binding : FragmentUpdateContactDialogBinding? = null
    private val binding get() = _binding!!

    //late instantiation of object of ContactViewModel
    private lateinit var viewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setting the style of the alert dialog
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //current class is binded to the xml file
        _binding = FragmentUpdateContactDialogBinding.inflate(inflater,container,false)

        //viewModel is instantiated
        viewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.inputLayoutName.setText(contact.fullName)
        binding.inputLayoutContact.setText(contact.contactNumber)

        //when save button is clicked
        binding.btnUpdateContact.setOnClickListener{
            //the text in inputLayoutname is stored as full name
            val fullName = binding.inputLayoutName.text.toString().trim()
            //the text in inputLayoutContact is saved to contactNumber
            val contactNumber = binding.inputLayoutContact.text.toString().trim()

            //if the full name is empty is prompts user there is an error
            if(fullName.isEmpty()){
                binding.inputLayoutName.error = "This field is required"
                return@setOnClickListener
            }
            if(contactNumber.isEmpty()){
                binding.inputLayoutContact.error = "this field is required"
                return@setOnClickListener
            }


            //set to the current object
            contact.fullName = fullName
            contact.contactNumber = contactNumber

            //the object is pass to addContact
            viewModel.updateContact(contact)
            dismiss()
            Toast.makeText(context, "Contact has been updated.", Toast.LENGTH_SHORT).show()

        }

    }

}