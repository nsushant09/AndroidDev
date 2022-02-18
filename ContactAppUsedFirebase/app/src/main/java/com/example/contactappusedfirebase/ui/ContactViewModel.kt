package com.example.contactappusedfirebase.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactappusedfirebase.data.Contact
import com.example.contactappusedfirebase.data.NODE_CONTACTS
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class ContactViewModel : ViewModel() {

    //create object for database connection
    private val dbcontacts = FirebaseDatabase.getInstance("https://contactlistproject-340a9-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference(NODE_CONTACTS)

    //check if data is submitted sucessfully or not

    //store result in here if it is null data is sucessfully added otherwise failure
    private val _result = MutableLiveData<Exception?>()
    val result : LiveData<Exception?> get() = _result

    fun addContact(contact : Contact){
        contact.id = dbcontacts.push().key

        dbcontacts.child(contact.id!!).setValue(contact).addOnCompleteListener{
            if(it.isSuccessful){
                _result.value = null
            }else{
                _result.value = it.exception
            }
        }
    }
}